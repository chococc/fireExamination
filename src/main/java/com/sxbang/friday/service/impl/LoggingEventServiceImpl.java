package com.sxbang.friday.service.impl;

import java.util.Map;

import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.base.result.ResponseCode;
import com.sxbang.friday.dao.LoggingEventDao;
import com.sxbang.friday.model.LoggingEvent;
import com.sxbang.friday.service.LoggingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class  LoggingEventServiceImpl implements LoggingEventService {

    @Autowired
    private LoggingEventDao loggingEventDao;

    @Override
    public Results<LoggingEvent> getByPage(Map<String, Object> params,Integer offset, Integer limit) {
        return new Results(0,"",null,loggingEventDao.count(params), loggingEventDao.list(params,offset, limit));

    }

    @Override
    public Results save(LoggingEvent loggingEvent) {
        int res = 0;
        if(StringUtils.isEmpty(loggingEvent.getTimestmp())){
            res =  loggingEventDao.save(loggingEvent);
        }else{
            res =  loggingEventDao.update(loggingEvent);
        }
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    public Results getById(String id) {
        LoggingEvent res =  loggingEventDao.getById(id);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    public LoggingEvent getLoggingEventById(String id) {
        return loggingEventDao.getById(id);
    }

    @Override
    @Transactional
    public Results update(LoggingEvent loggingEvent) {
        int res =  loggingEventDao.update(loggingEvent);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    @Transactional
    public Results delete(LoggingEvent loggingEvent) {
        int res =  loggingEventDao.delete(loggingEvent);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

}
