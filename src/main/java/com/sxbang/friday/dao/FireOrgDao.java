package com.sxbang.friday.dao;

import java.util.List;
import java.util.Map;

import com.sxbang.friday.model.FireOrg;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface FireOrgDao {

    @Select("select * from fire_org t where t.org_id = #{id}")
    FireOrg getById(Integer id);

    @Delete("delete from fire_org where org_id = #{orgId}")
    int delete(FireOrg fireOrg);

    int update(FireOrg fireOrg);
    
    @Options(useGeneratedKeys = true, keyProperty = "orgId")
    @Insert("insert into fire_org(org_id, org_name, pay_number, address, phone, account) values(#{orgId}, #{orgName}, #{payNumber}, #{address}, #{phone}, #{account})")
    int save(FireOrg fireOrg);
    
    int count(@Param("params") Map<String, Object> params);

    List<FireOrg> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

}
