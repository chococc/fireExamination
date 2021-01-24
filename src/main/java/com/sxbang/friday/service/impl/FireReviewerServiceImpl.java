package com.sxbang.friday.service.impl;

import java.util.Date;
import java.util.Map;

import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.base.result.ResponseCode;
import com.sxbang.friday.dao.FireReviewerDao;
import com.sxbang.friday.model.FireReviewer;
import com.sxbang.friday.service.FireReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class  FireReviewerServiceImpl implements FireReviewerService {

    @Autowired
    private FireReviewerDao fireReviewerDao;

    @Override
    public Results<FireReviewer> getByPage(Map<String, Object> params, Integer offset, Integer limit) {
        return new Results(0,"",null,fireReviewerDao.count(params), fireReviewerDao.list(params,offset, limit));

    }

    @Override
    public Results save(FireReviewer fireReviewer) {
        int res = 0;
        if(StringUtils.isEmpty(fireReviewer.getReviewerId())){
            fireReviewer.setLastTime(new Date());
            fireReviewer.setTimes(0);
            res =  fireReviewerDao.save(fireReviewer);
        }else{
            res =  fireReviewerDao.update(fireReviewer);
        }
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    public Results getById(Integer id) {
        FireReviewer res =  fireReviewerDao.getById(id);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    public FireReviewer getFireReviewerById(Integer id) {
        return fireReviewerDao.getById(id);
    }

    @Override
    @Transactional
    public Results update(FireReviewer fireReviewer) {
        int res =  fireReviewerDao.update(fireReviewer);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    @Transactional
    public Results delete(FireReviewer fireReviewer) {
        int res =  fireReviewerDao.delete(fireReviewer);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

}
