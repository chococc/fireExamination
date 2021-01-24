package com.sxbang.friday.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.base.result.ResponseCode;
import com.sxbang.friday.dao.*;
import com.sxbang.friday.model.*;
import com.sxbang.friday.service.FireOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class  FireOrderServiceImpl implements FireOrderService {

    @Autowired
    private FireOrderDao fireOrderDao;

    @Autowired
    private FireLevelDao fireLevelDao;

    @Autowired
    private FireExamineDao fireExamineDao;

    @Autowired
    private FireExamineRoomDao fireExamineRoomDao;
    @Autowired
    private  UserDao  userDao;

    @Override
    public Results<FireOrder> getByPage(Map<String, Object> params,Integer offset, Integer limit) {

        List<FireOrder> fireExamineList= fireOrderDao.list(params,offset, limit);
        List<FireOrder> results= fireExamineList.stream().map(fireOrder -> {
            try {
                SysUser user= userDao.getById(fireOrder.getExamId().longValue());
                fireOrder.setExamName(user.getNickname());
                FireLevel fireLevel=  fireLevelDao.getById(fireOrder.getLevelId());
                fireOrder.setLevel(fireLevel.getLevel());
            }catch (Exception e){
                e.printStackTrace();
            }

            try {
             FireExamine  examine=   fireExamineDao.getById(fireOrder.getExamineId());
                fireOrder.setStartTime(examine.getStartTime());
                fireOrder.setEndTime(examine.getEndTime());
                fireOrder.setDate(examine.getDate());
            }catch (Exception e){
                e.printStackTrace();
            }
            return  fireOrder;
        }).collect(Collectors.toList());
        return new Results(0,"",null,fireOrderDao.count(params), results);

    }

    @Override
    public Results save(FireOrder fireOrder) {
        int res = 0;
        if(StringUtils.isEmpty(fireOrder.getOrderId())){
            res =  fireOrderDao.save(fireOrder);
        }else{
            res =  fireOrderDao.update(fireOrder);
        }
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    public Results getById(Integer id) {
        FireOrder res =  fireOrderDao.getById(id);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    public FireOrder getFireOrderById(Integer id) {
        return fireOrderDao.getById(id);
    }

    @Override
    @Transactional
    public Results update(FireOrder fireOrder) {
        int res =  fireOrderDao.update(fireOrder);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    @Transactional
    public Results delete(FireOrder fireOrder) {
        int res =  fireOrderDao.delete(fireOrder);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

}
