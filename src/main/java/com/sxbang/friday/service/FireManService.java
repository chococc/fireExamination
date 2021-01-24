package com.sxbang.friday.service;


import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.model.FireMan;

import java.util.List;
import java.util.Map;

public interface FireManService {

   Results<FireMan> getByPage(Map<String, Object> params, Integer offset, Integer limit);

   Results  save(FireMan fireMan);

   Results  getById(Integer id);

   Results update(FireMan fireMan);

   Results  delete(FireMan fireMan);

   FireMan getFireManById(Integer id);

    void importUser(List<Map<Integer, String>> list);
}

