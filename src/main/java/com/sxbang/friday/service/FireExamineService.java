package com.sxbang.friday.service;


import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.model.FireExamine;

import java.util.Map;

public interface FireExamineService {

   Results<FireExamine> getByPage(Map<String, Object> params, Integer offset, Integer limit);

   Results  save(FireExamine fireExamine);

   Results  getById(Integer id);

   Results update(FireExamine fireExamine);

   Results  delete(FireExamine fireExamine);

   FireExamine getFireExamineById(Integer id);

}

