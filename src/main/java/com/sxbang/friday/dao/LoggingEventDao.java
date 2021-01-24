package com.sxbang.friday.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sxbang.friday.model.LoggingEvent;

@Mapper
public interface LoggingEventDao {

    @Select("select * from logging_event t where t.timestmp = #{id}")
    LoggingEvent getById(String id);

    @Delete("delete from logging_event where timestmp = #{timestmp}")
    int delete(LoggingEvent loggingEvent);

    int update(LoggingEvent loggingEvent);
    
    @Options(useGeneratedKeys = true, keyProperty = "timestmp")
    @Insert("insert into logging_event(timestmp, formatted_message, logger_name, level_string, thread_name, reference_flag, arg0, arg1, arg2, arg3, caller_filename, caller_class, caller_method, caller_line, event_id) values(#{timestmp}, #{formattedMessage}, #{loggerName}, #{levelString}, #{threadName}, #{referenceFlag}, #{arg0}, #{arg1}, #{arg2}, #{arg3}, #{callerFilename}, #{callerClass}, #{callerMethod}, #{callerLine}, #{eventId})")
    int save(LoggingEvent loggingEvent);
    
    int count(@Param("params") Map<String, Object> params);

    List<LoggingEvent> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

}
