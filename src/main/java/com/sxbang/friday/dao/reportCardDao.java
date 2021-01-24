package com.sxbang.friday.dao;

import com.sxbang.friday.model.reportCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface reportCardDao {
    int count(@Param("params") Map<String, Object> params);

    List<reportCard> getByID(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit,@Param("testID") String testID);
}
