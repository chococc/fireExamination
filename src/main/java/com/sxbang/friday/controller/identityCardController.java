package com.sxbang.friday.controller;

import com.sxbang.friday.base.result.PageTableRequest;
import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.service.identityCardService;
import com.sxbang.friday.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sxbang.friday.model.testidentity;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "identityCard")
public class identityCardController {
    @Autowired(required=false)
    private identityCardService identityCardService;

    @GetMapping(value = "Cardadmin")
    @ApiOperation(value = "查看准考证_管理员用",notes="查看准考证列表")
    @ResponseBody
    public Results<testidentity> identityList(PageTableRequest request, testidentity testidentity){
        request.countOffset();
        Map<String, Object> param = null;
        try {
            param =   StrUtil.convertToMap(testidentity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return identityCardService.getByPage(param, request.getOffset(), request.getLimit());
    }
}
