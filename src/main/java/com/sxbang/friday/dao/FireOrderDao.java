package com.sxbang.friday.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sxbang.friday.model.FireOrder;

@Mapper
public interface FireOrderDao {

    @Select("select * from fire_order t where t.order_id = #{orderId}")
    FireOrder getById(Integer id);

    @Delete("delete from fire_order where order_id = #{orderId}")
    int delete(FireOrder fireOrder);

    int update(FireOrder fireOrder);
    
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    @Insert("insert into fire_order(order_id, examine_id, examine_name, level_id, exam_id, create_time, money, status) values(#{orderId}, #{examineId}, #{examineName}, #{levelId}, #{examId}, #{createTime}, #{money}, #{status})")
    int save(FireOrder fireOrder);
    
    int count(@Param("params") Map<String, Object> params);

    List<FireOrder> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

}
