package com.sxbang.friday.service.impl;

import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.model.testidentity;
import com.sxbang.friday.service.identityCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class testidentityImpl implements identityCardService {
    @Autowired(required = false)
    com.sxbang.friday.dao.identityCardDao identityCardDao;
    @Override
    public Results<testidentity> getByPage(Map<String, Object> params, Integer offset, Integer limit) {
        return new Results(0,"",null,identityCardDao.count(params), identityCardDao.list(params,offset, limit));
    }

}
