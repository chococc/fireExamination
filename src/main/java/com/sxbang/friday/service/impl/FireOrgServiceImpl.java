package com.sxbang.friday.service.impl;

import java.util.Map;

import com.sxbang.friday.base.result.ResponseCode;
import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.dao.FireOrgDao;
import com.sxbang.friday.model.FireOrg;
import com.sxbang.friday.service.FireOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class  FireOrgServiceImpl implements FireOrgService {

    @Autowired
    private FireOrgDao fireOrgDao;

    @Override
    public Results<FireOrg> getByPage(Map<String, Object> params, Integer offset, Integer limit) {
        return new Results(0,"",null,fireOrgDao.count(params), fireOrgDao.list(params,offset, limit));

    }

    @Override
    public Results save(FireOrg fireOrg) {
        int res = 0;
        if(StringUtils.isEmpty(fireOrg.getOrgId())){
            res =  fireOrgDao.save(fireOrg);
        }else{
            res =  fireOrgDao.update(fireOrg);
        }
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    public Results getById(Integer id) {
        FireOrg res =  fireOrgDao.getById(id);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    public FireOrg getFireOrgById(Integer id) {
        return fireOrgDao.getById(id);
    }

    @Override
    @Transactional
    public Results update(FireOrg fireOrg) {
        int res =  fireOrgDao.update(fireOrg);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    @Transactional
    public Results delete(FireOrg fireOrg) {
        int res =  fireOrgDao.delete(fireOrg);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

}
