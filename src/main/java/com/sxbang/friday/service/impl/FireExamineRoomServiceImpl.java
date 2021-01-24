package com.sxbang.friday.service.impl;

import java.util.Map;

import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.base.result.ResponseCode;
import com.sxbang.friday.dao.FireExamineRoomDao;
import com.sxbang.friday.model.FireExamineRoom;
import com.sxbang.friday.service.FireExamineRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class  FireExamineRoomServiceImpl implements FireExamineRoomService {

    @Autowired
    private FireExamineRoomDao fireExamineRoomDao;

    @Override
    public Results<FireExamineRoom> getByPage(Map<String, Object> params, Integer offset, Integer limit) {
        return new Results(0,"",null,fireExamineRoomDao.count(params), fireExamineRoomDao.list(params,offset, limit));

    }

    @Override
    public Results save(FireExamineRoom fireExamineRoom) {
        int res = 0;
        if(StringUtils.isEmpty(fireExamineRoom.getExamineRoomId())){
            res =  fireExamineRoomDao.save(fireExamineRoom);
        }else{
            res =  fireExamineRoomDao.update(fireExamineRoom);
        }
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    public Results getById(Integer id) {
        FireExamineRoom res =  fireExamineRoomDao.getById(id);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    public FireExamineRoom getFireExamineRoomById(Integer id) {
        return fireExamineRoomDao.getById(id);
    }

    @Override
    @Transactional
    public Results update(FireExamineRoom fireExamineRoom) {
        int res =  fireExamineRoomDao.update(fireExamineRoom);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    @Transactional
    public Results delete(FireExamineRoom fireExamineRoom) {
        int res =  fireExamineRoomDao.delete(fireExamineRoom);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

}
