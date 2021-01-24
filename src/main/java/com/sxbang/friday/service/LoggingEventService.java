package com.sxbang.friday.service;

import com.sxbang.friday.model.LoggingEvent;
import com.sxbang.friday.base.result.Results;

import java.util.Map;

public interface LoggingEventService {

   Results<LoggingEvent>  getByPage(Map<String, Object> params, Integer offset, Integer limit);

   Results  save(LoggingEvent loggingEvent);

   Results  getById(String id);

   Results update(LoggingEvent loggingEvent);

   Results  delete(LoggingEvent loggingEvent);

   LoggingEvent getLoggingEventById(String id);

}

