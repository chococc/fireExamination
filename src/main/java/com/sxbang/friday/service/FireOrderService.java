package com.sxbang.friday.service;

import com.sxbang.friday.model.FireOrder;
import com.sxbang.friday.base.result.Results;

import java.util.Map;

public interface FireOrderService {

   Results<FireOrder>  getByPage(Map<String, Object> params, Integer offset, Integer limit);

   Results  save(FireOrder fireOrder);

   Results  getById(Integer id);

   Results update(FireOrder fireOrder);

   Results  delete(FireOrder fireOrder);

   FireOrder getFireOrderById(Integer id);

}

