package com.sxbang.friday.dao;

import java.util.List;
import java.util.Map;

import com.sxbang.friday.model.FireExamine;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface FireExamineDao {

    @Select("select * from fire_examine t where t.examine_id = #{id}")
    FireExamine getById(Integer id);

    @Delete("delete from fire_examine where examine_id = #{examineId}")
    int delete(FireExamine fireExamine);

    int update(FireExamine fireExamine);
    
    @Options(useGeneratedKeys = true, keyProperty = "examineId")
    @Insert("insert into fire_examine(examine_id, level_id, money, date, start_time, end_time, number, order_number, examine_room_id, status ,firviewId_one, firviewId_two) values(#{examineId}, #{levelId}, #{money}, #{date}, #{startTime}, #{endTime}, #{number}, #{orderNumber}, #{examineRoomId}, #{status}, #{firviewIdOne}, #{firviewIdTwo})")
    int save(FireExamine fireExamine);
    
    int count(@Param("params") Map<String, Object> params);

    List<FireExamine> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

}
