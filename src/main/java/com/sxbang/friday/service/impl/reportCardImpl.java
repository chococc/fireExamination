package com.sxbang.friday.service.impl;

import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.model.reportCard;
import com.sxbang.friday.service.reportCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class reportCardImpl implements reportCardService {
    @Autowired(required = false)
    private com.sxbang.friday.dao.reportCardDao reportCardDao;
    @Override
    public Results<reportCard> getByID(Map<String, Object> params, Integer offset, Integer limit,String testID){
        return new Results(0,"",null,reportCardDao.count(params), reportCardDao.getByID(params,offset, limit,testID));
    }
}
