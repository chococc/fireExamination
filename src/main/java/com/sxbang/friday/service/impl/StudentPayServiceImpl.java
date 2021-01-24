package com.sxbang.friday.service.impl;

import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.config.ServerConfig;
import com.sxbang.friday.dao.FireExamineDao;
import com.sxbang.friday.dao.FireOrderDao;
import com.sxbang.friday.dto.LoginUser;
import com.sxbang.friday.model.FireExamine;
import com.sxbang.friday.model.FireOrder;
import com.sxbang.friday.mylock.lock.redis.RedisReentrantLock;
import com.sxbang.friday.mylock.redis.AccessSpeedLimit;
import com.sxbang.friday.service.FireOrderService;
import com.sxbang.friday.service.StudentPayService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Log
public class StudentPayServiceImpl implements StudentPayService {


    @Autowired
    ServerConfig serverConfig;
    //JedisPool方法
    @Autowired
    private JedisPool jedisPool;

    @Autowired
    FireOrderDao fireOrderDao;

    @Autowired
    FireExamineDao fireExamineDao;

    @Override
    public Results<FireOrder> orderExamine(Integer examineId) throws InterruptedException {
        String port=serverConfig.getPort()+"";
        String loackId="fire_examine"+examineId;
        Long xian= Thread.currentThread().getId();
        log.info("########################开始#################"+new Date());
        log.info("开始获取锁ID:"+loackId+",端口是："+port+",线程ID"+xian);
        RedisReentrantLock lock=new RedisReentrantLock(jedisPool,loackId);
        LoginUser loginUser=null;
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication instanceof AnonymousAuthenticationToken) {
                return null;
            }

            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                loginUser= (LoginUser)authentication.getPrincipal();
            }
        }

        Results<FireOrder> results;
        try {
            if (lock.tryLock(50000L, TimeUnit.MILLISECONDS)) {
                log.info("成功获取到锁ID:"+loackId+",端口是："+port);
                log.info("--------------------"+xian+"开始操作-----------------------------");
                log.info("获取到锁了线程号："+xian);
                // 获取锁
                FireOrder  fireOrder=new FireOrder();
                FireExamine fireExamine=  fireExamineDao.getById(examineId);
                if (fireExamine.getOrderNumber()==null)fireExamine.setOrderNumber(0);
                if (fireExamine.getNumber()-fireExamine.getOrderNumber()<=0){
                    //超时
                    results=Results.failure(400,"预约已满");
                }else {
                    fireExamine.setOrderNumber(fireExamine.getOrderNumber()+1);
                    fireExamineDao.update(fireExamine);
                    if (loginUser!=null){
                        fireOrder.setExamId(loginUser.getId().intValue());
                    }
                    fireOrder.setExamineId(fireExamine.getExamineId());
                    fireOrder.setMoney(fireExamine.getMoney());
                    fireOrder.setLevelId(fireExamine.getLevelId());
                    fireOrder.setCreateTime(new Date());
                    fireOrder.setStatus(1); // 未支付
                    fireOrderDao.save(fireOrder);
                    results=Results.success(fireOrder);
                }
            }else{
                //超时
                results=Results.failure(400,"获取锁失败");
            }
//            Thread.sleep(3000);
        }finally {
            log.info("--------------------"+xian+"结束操作-----------------------------");
            lock.unlock();
            log.info("成功获释放锁ID:"+loackId+",端口是："+port);
        }

        log.info("########################结束#################"+new Date());
        return results;
    }

    @Override
    public Results<FireOrder> payExamine(Integer orderId) {
        FireOrder order=  fireOrderDao.getById(orderId);
        Results<FireOrder> results;
        if (order==null){
            //超时
            results=Results.failure(400,"支付失败");
        }else {
            order.setStatus(2); // 已支付
            fireOrderDao.update(order);
            results=Results.success(order);
        }

        return results;
    }

    @Override
    public Results<FireOrder> startExamine(Integer orderId) {
        FireOrder order=  fireOrderDao.getById(orderId);
        Results<FireOrder> results;
        if (order==null){
            //超时
            results=Results.failure(400,"考试失败");
        }else {
            order.setStatus(3); // 考试中
            fireOrderDao.update(order);
            results=Results.success(order);
        }
        return results;
    }

    @Override
    public Results<FireOrder> finishExamine(Integer orderId) {
        FireOrder order=  fireOrderDao.getById(orderId);
        Results<FireOrder> results;
        if (order==null){
            //超时
            results=Results.failure(400,"结束失败");
        }else {
            order.setStatus(4); // 结束
            fireOrderDao.update(order);
            results=Results.success(order);
        }
        return results;
    }

    @Override
    public Results<FireOrder> viewExamine(Integer orderId) {
        FireOrder order=  fireOrderDao.getById(orderId);
        Results<FireOrder> results;
        if (order==null){
            //超时
            results=Results.failure(400,"评卷失败");
        }else {
            order.setStatus(5); // 结束
            fireOrderDao.update(order);
            results=Results.success(order);
        }
        return results;
    }
}
