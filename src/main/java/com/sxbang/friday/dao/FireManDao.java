package com.sxbang.friday.dao;

import java.util.List;
import java.util.Map;

import com.sxbang.friday.model.FireMan;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface FireManDao {

    @Select("select * from fire_man t where t.examine_userid = #{id}")
    FireMan getById(Integer id);

    @Delete("delete from fire_man where examine_userid = #{examineUserid}")
    int delete(FireMan fireMan);

    int update(FireMan fireMan);
    
    @Options(useGeneratedKeys = true, keyProperty = "examineUserid")
    @Insert("insert into fire_man(examine_userid, examine_username, org_id, level_id, level, telephone, identity_number) values(#{examineUserid}, #{examineUsername}, #{orgId}, #{levelId}, #{level}, #{telephone}, #{identityNumber})")
    int save(FireMan fireMan);
    
    int count(@Param("params") Map<String, Object> params);

    List<FireMan> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

}
