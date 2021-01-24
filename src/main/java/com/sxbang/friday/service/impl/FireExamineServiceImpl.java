package com.sxbang.friday.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import com.sxbang.friday.base.result.ResponseCode;
import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.dao.FireExamineDao;
import com.sxbang.friday.dao.FireExamineRoomDao;
import com.sxbang.friday.dao.FireLevelDao;
import com.sxbang.friday.dao.FireReviewerDao;
import com.sxbang.friday.model.FireExamine;
import com.sxbang.friday.model.FireExamineRoom;
import com.sxbang.friday.model.FireLevel;
import com.sxbang.friday.model.FireReviewer;
import com.sxbang.friday.service.FireExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class  FireExamineServiceImpl implements FireExamineService {

    @Autowired
    private FireExamineDao fireExamineDao;

    @Autowired
    private FireLevelDao fireLevelDao;

    @Autowired
    private FireExamineRoomDao fireExamineRoomDao;

    @Autowired
    private FireReviewerDao fireReviewerDao;

    @Override
    public Results<FireExamine> getByPage(Map<String, Object> params, Integer offset, Integer limit) {
        List<FireExamine> fireExamineList= fireExamineDao.list(params,offset, limit);
        List<FireExamine> results= fireExamineList.stream().map(fireExamine -> {
            try {
                FireExamineRoom fireExamineRoom= fireExamineRoomDao.getById(fireExamine.getExamineRoomId());
                fireExamine.setExamineName(fireExamineRoom.getExamineRoomName());
                FireLevel fireLevel=  fireLevelDao.getById(fireExamine.getLevelId());
                fireExamine.setLevel(fireLevel.getLevel());

            }catch (Exception e){
                e.printStackTrace();
            }
            try {

                FireReviewer reviewer1= fireReviewerDao.getById(fireExamine.getFirviewIdOne());
                //fireExamine.setFirviewIdOneName(reviewer1.getReviewerName());
                FireReviewer reviewer2= fireReviewerDao.getById(fireExamine.getFirviewIdTwo());
                //fireExamine.setFirviewIdTwoName(reviewer2.getReviewerName());
            }catch (Exception e){
                e.printStackTrace();
            }
            return  fireExamine;
        }).collect(Collectors.toList());
        return new Results(0,"",null,fireExamineDao.count(params), results);

    }

    @Override
    public Results save(FireExamine fireExamine) {
        int res = 0;
        if(0==fireExamine.getExamineId()){
            fireExamine.setOrderNumber(0);
          FireLevel fireLevel=  fireLevelDao.getById(fireExamine.getLevelId());
            fireExamine.setMoney(fireLevel.getMoney());
            try {
                List<FireReviewer> reviewers= fireReviewerDao.list(new HashMap<>(),0,10000);
                fireExamine.setFirviewIdOne(reviewers.get((int) (Math.random()*(reviewers.size()))).getReviewerId());
                fireExamine.setFirviewIdTwo(reviewers.get((int) (Math.random()*(reviewers.size()))).getReviewerId());
            }catch (Exception e){
                e.printStackTrace();
            }
            res =  fireExamineDao.save(fireExamine);
        }else{
            res =  fireExamineDao.update(fireExamine);
        }
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    public Results getById(Integer id) {
        FireExamine res =  fireExamineDao.getById(id);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        results.setData(res);
        return results;
    }

    @Override
    public FireExamine getFireExamineById(Integer id) {
        return fireExamineDao.getById(id);
    }

    @Override
    @Transactional
    public Results update(FireExamine fireExamine) {
        int res =  fireExamineDao.update(fireExamine);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    @Transactional
    public Results delete(FireExamine fireExamine) {
        int res =  fireExamineDao.delete(fireExamine);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    public static void main(String[] args) {
        System.out.println(  (int) (Math.random()*(2)));
    }

}
