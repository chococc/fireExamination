package com.sxbang.friday.service.impl;

import java.util.Map;

import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.base.result.ResponseCode;
import com.sxbang.friday.dao.FireLevelDao;
import com.sxbang.friday.model.FireLevel;
import com.sxbang.friday.service.FireLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class  FireLevelServiceImpl implements FireLevelService {

    @Autowired
    private FireLevelDao fireLevelDao;

    @Override
    public Results<FireLevel> getByPage(Map<String, Object> params, Integer offset, Integer limit) {
        return new Results(0,"",null,fireLevelDao.count(params), fireLevelDao.list(params,offset, limit));

    }

    @Override
    public Results save(FireLevel fireLevel) {
        int res = 0;
        if(StringUtils.isEmpty(fireLevel.getLevelId())){
            res =  fireLevelDao.save(fireLevel);
        }else{
            res =  fireLevelDao.update(fireLevel);
        }
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    public Results getById(Integer id) {
        FireLevel res =  fireLevelDao.getById(id);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    public FireLevel getFireLevelById(Integer id) {
        return fireLevelDao.getById(id);
    }

    @Override
    @Transactional
    public Results update(FireLevel fireLevel) {
        int res =  fireLevelDao.update(fireLevel);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    @Transactional
    public Results delete(FireLevel fireLevel) {
        int res =  fireLevelDao.delete(fireLevel);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

}
