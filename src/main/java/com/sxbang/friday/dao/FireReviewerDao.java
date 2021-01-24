package com.sxbang.friday.dao;

import java.util.List;
import java.util.Map;

import com.sxbang.friday.model.FireReviewer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface FireReviewerDao {

    @Select("select * from fire_reviewer t where t.reviewer_id = #{id}")
    FireReviewer getById(Integer id);

    @Delete("delete from fire_reviewer where reviewer_id = #{reviewerId}")
    int delete(FireReviewer fireReviewer);

    int update(FireReviewer fireReviewer);
    
    @Options(useGeneratedKeys = true, keyProperty = "reviewerId")
    @Insert("insert into fire_reviewer(reviewer_id, reviewer_name, sex, age, times, last_time) values(#{reviewerId}, #{reviewerName}, #{sex}, #{age}, #{times}, #{lastTime})")
    int save(FireReviewer fireReviewer);
    
    int count(@Param("params") Map<String, Object> params);

    List<FireReviewer> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

}
