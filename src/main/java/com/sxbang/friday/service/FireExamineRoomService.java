package com.sxbang.friday.service;


import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.model.FireExamineRoom;

import java.util.Map;

public interface FireExamineRoomService {

   Results<FireExamineRoom> getByPage(Map<String, Object> params, Integer offset, Integer limit);

   Results  save(FireExamineRoom fireExamineRoom);

   Results  getById(Integer id);

   Results update(FireExamineRoom fireExamineRoom);

   Results  delete(FireExamineRoom fireExamineRoom);

   FireExamineRoom getFireExamineRoomById(Integer id);

}

