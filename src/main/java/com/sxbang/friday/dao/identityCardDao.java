package com.sxbang.friday.dao;

import com.sxbang.friday.model.testidentity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface identityCardDao {
    @Select("select * from test_identity t where t.testNo = #{id}")
    testidentity getById(Integer id);

    @Delete("delete from test_identity where testNo = #{testNo}")
    int delete(testidentity testidentity);

    int update(testidentity testidentity);

    int count(@Param("params") Map<String, Object> params);

    List<testidentity> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
