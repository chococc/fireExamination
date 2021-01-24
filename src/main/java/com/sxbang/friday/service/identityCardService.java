package com.sxbang.friday.service;

import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.model.testidentity;

import java.util.Map;

public interface identityCardService {
    Results<testidentity> getByPage(Map<String, Object> params, Integer offset, Integer limit);
}
