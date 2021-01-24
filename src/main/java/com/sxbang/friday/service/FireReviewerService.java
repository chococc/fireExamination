package com.sxbang.friday.service;

import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.model.FireReviewer;

import java.util.Map;

public interface FireReviewerService {

   Results<FireReviewer>  getByPage(Map<String, Object> params, Integer offset, Integer limit);

   Results  save(FireReviewer fireReviewer);

   Results  getById(Integer id);

   Results update(FireReviewer fireReviewer);

   Results  delete(FireReviewer fireReviewer);

   FireReviewer getFireReviewerById(Integer id);

}

