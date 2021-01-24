package com.sxbang.friday.service;

import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.model.FireOrg;

import java.util.Map;

public interface FireOrgService {

   Results<FireOrg> getByPage(Map<String, Object> params, Integer offset, Integer limit);

   Results  save(FireOrg fireOrg);

   Results  getById(Integer id);

   Results update(FireOrg fireOrg);

   Results  delete(FireOrg fireOrg);

   FireOrg getFireOrgById(Integer id);

}

