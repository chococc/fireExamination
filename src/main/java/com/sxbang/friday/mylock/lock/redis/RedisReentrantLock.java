package com.sxbang.friday.mylock.lock.redis;

import com.google.common.collect.Maps;
import com.sxbang.friday.mylock.lock.DistributedReentrantLock;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class RedisReentrantLock implements DistributedReentrantLock {

    private final ConcurrentMap<Thread, LockData> threadData = Maps.newConcurrentMap();

    private JedisPool jedisPool;

    private RedisLockInternals internals;

    private String lockId;

    /**
     * 把线程池创建
     * @param jedisPool
     * @param lockId
     */
    public RedisReentrantLock(JedisPool jedisPool, String lockId) {
        this.jedisPool = jedisPool;
        this.lockId=lockId;
        this.internals=new RedisLockInternals(jedisPool);
    }

    /**
     * 线程锁的数据
     */
    private static class LockData {
        final Thread owningThread;
        final String lockVal;
        /**
         *  java 的原子 操作，不会因为多线程导致内存一直
         */
        final AtomicInteger lockCount = new AtomicInteger(1);

        private LockData(Thread owningThread, String lockVal) {
            this.owningThread = owningThread;
            this.lockVal = lockVal;
        }
    }

    /**
     * 获取锁
     * @param timeout
     * @param unit
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException{
        Thread currentThread = Thread.currentThread();
        /**
         * 看下当前线程上是否有锁
         */
        LockData lockData = threadData.get(currentThread);
        if ( lockData != null ) {
            /**
             * 这个代表本机已经获取到了锁，只是她可以多执行几次
             *
             * 获取到了这个锁之后可以在继续（本机）可以在此获取  lockCount 去标识
             *
             * 就是重入锁的概念
             */
            lockData.lockCount.incrementAndGet();
            return true;
        }
        /**
         * 这个代表的是没找到锁 可能是其他主机上的 要去获取锁
         */
        String lockVal = internals.tryRedisLock(lockId,timeout,unit);
        if ( lockVal != null ) {
            /**
             * 这个代表已经在redis上设置了key  已经获取到了锁，创建锁的内容标识
             */
            LockData newLockData = new LockData(currentThread, lockVal);
            threadData.put(currentThread, newLockData);
            return true;
        }
        return false;
    }

    @Override
    public void unlock() {
        Thread currentThread = Thread.currentThread();
        LockData lockData = threadData.get(currentThread);
        if ( lockData == null ) {
            throw new IllegalMonitorStateException("You do not own the lock: " + lockId);
        }
        /**
         * 重入锁是否
         */
        int newLockCount = lockData.lockCount.decrementAndGet();
        if ( newLockCount > 0 ) {
            return;
        }
        if ( newLockCount < 0 ) {
            throw new IllegalMonitorStateException("Lock count has gone negative for lock: " + lockId);
        }
        try {
            /**
             * 如果本机的重入锁 已经都释放完了之后，删除redis的key字段 让别的主机也可以在继续抢
             */
            internals.unlockRedisLock(lockId,lockData.lockVal);
        } finally {
            threadData.remove(currentThread);
        }
    }
}
