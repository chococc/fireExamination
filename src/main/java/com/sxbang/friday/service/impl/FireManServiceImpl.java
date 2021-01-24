package com.sxbang.friday.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sxbang.friday.base.result.ResponseCode;
import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.dao.FireLevelDao;
import com.sxbang.friday.dao.FireManDao;
import com.sxbang.friday.dao.FireOrgDao;
import com.sxbang.friday.model.FireExamineRoom;
import com.sxbang.friday.model.FireLevel;
import com.sxbang.friday.model.FireMan;
import com.sxbang.friday.model.FireOrg;
import com.sxbang.friday.service.FireManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class  FireManServiceImpl implements FireManService {

    @Autowired
    private FireManDao fireManDao;

    @Autowired
    private FireLevelDao fireLevelDao;

    @Autowired
    private FireOrgDao fireOrgDao;

    @Override
    public Results<FireMan> getByPage(Map<String, Object> params, Integer offset, Integer limit) {

       List<FireMan> fireManList= fireManDao.list(params,offset, limit);
        List<FireMan> results= fireManList.stream().map(fireMan -> {
            try {
                FireLevel fireLevel=  fireLevelDao.getById(fireMan.getLevelId());
                fireMan.setLevel(fireLevel.getLevel());
                FireOrg fireOrg= fireOrgDao.getById(fireMan.getOrgId());
                fireMan.setOrgName(fireOrg.getOrgName());
            }catch (Exception e){
                e.printStackTrace();
            }

            return fireMan;
        }).collect(Collectors.toList());


        return new Results(0,"",null,fireManDao.count(params), results);

    }

    @Override
    public Results save(FireMan fireMan) {
        int res = 0;
        if(StringUtils.isEmpty(fireMan.getExamineUserid())){
            res =  fireManDao.save(fireMan);
        }else{
            res =  fireManDao.update(fireMan);
        }
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    public Results getById(Integer id) {
        FireMan res =  fireManDao.getById(id);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    public FireMan getFireManById(Integer id) {
        return fireManDao.getById(id);
    }

    @Override
    public void importUser(List<Map<Integer, String>> list) {
        for (int i = 1; i <list.size() ; i++) {
        FireMan    fireMan =new FireMan();
            Map<Integer, String>  data=list.get(i);
            fireMan.setExamineUsername(data.get(1));

            FireLevel fireLevel=  fireLevelDao.getById(Integer.parseInt(data.get(3)));
            fireMan.setLevel(fireLevel.getLevel());
            fireMan.setLevelId(fireLevel.getLevelId());
            FireOrg fireOrg= fireOrgDao.getById(Integer.parseInt(data.get(2)));
            fireMan.setOrgName(fireOrg.getOrgName());
            fireMan.setOrgId(fireOrg.getOrgId());
            fireMan.setTelephone(data.get(4));
            fireMan.setIdentityNumber(data.get(5));
            fireManDao.save(fireMan);
        }
    }

    @Override
    @Transactional
    public Results update(FireMan fireMan) {
        int res =  fireManDao.update(fireMan);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    @Transactional
    public Results delete(FireMan fireMan) {
        int res =  fireManDao.delete(fireMan);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

}
