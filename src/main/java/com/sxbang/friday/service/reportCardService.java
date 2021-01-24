package com.sxbang.friday.service;

import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.model.reportCard;

import java.util.Map;

public interface reportCardService {
    Results<reportCard> getByID(Map<String, Object> params, Integer offset, Integer limit,String testID);
}
