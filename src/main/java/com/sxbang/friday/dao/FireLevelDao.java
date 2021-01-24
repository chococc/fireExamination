package com.sxbang.friday.dao;

import java.util.List;
import java.util.Map;

import com.sxbang.friday.model.FireLevel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface FireLevelDao {

    @Select("select * from fire_level t where t.level_id = #{id}")
    FireLevel getById(Integer id);

    @Delete("delete from fire_level where level_id = #{levelId}")
    int delete(FireLevel fireLevel);

    int update(FireLevel fireLevel);
    
    @Options(useGeneratedKeys = true, keyProperty = "level_id")
    @Insert("insert into fire_level(level_id, level, money) values(#{levelId}, #{level}, #{money})")
    int save(FireLevel fireLevel);
    
    int count(@Param("params") Map<String, Object> params);

    List<FireLevel> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

}
