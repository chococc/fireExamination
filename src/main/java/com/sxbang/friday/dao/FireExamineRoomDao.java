package com.sxbang.friday.dao;

import java.util.List;
import java.util.Map;

import com.sxbang.friday.model.FireExamineRoom;
import org.apache.ibatis.annotations.*;


@Mapper
public interface FireExamineRoomDao {

    @Select("select * from fire_examine_room t where t.examine_room_id = #{id}")
    FireExamineRoom getById(Integer id);

    @Delete("delete from fire_examine_room where examine_room_id = #{examineRoomId}")
    int delete(FireExamineRoom fireExamineRoom);

    int update(FireExamineRoom fireExamineRoom);
    
    @Options(useGeneratedKeys = true, keyProperty = "examineRoomId")
    @Insert("insert into fire_examine_room(examine_room_id, examine_room_name, detail_address,storage) values(#{examineRoomId}, #{examineRoomName}, #{detailAddress},#{storage})")
    int save(FireExamineRoom fireExamineRoom);
    
    int count(@Param("params") Map<String, Object> params);

    List<FireExamineRoom> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

}
