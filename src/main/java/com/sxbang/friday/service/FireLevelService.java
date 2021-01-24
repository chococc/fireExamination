package com.sxbang.friday.service;

import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.model.FireLevel;

import java.util.Map;

public interface FireLevelService {

   Results<FireLevel>  getByPage(Map<String, Object> params, Integer offset, Integer limit);

   Results  save(FireLevel fireLevel);

   Results  getById(Integer id);

   Results update(FireLevel fireLevel);

   Results  delete(FireLevel fireLevel);

   FireLevel getFireLevelById(Integer id);

}

