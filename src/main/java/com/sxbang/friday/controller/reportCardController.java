package com.sxbang.friday.controller;

import com.sxbang.friday.base.result.PageTableRequest;
import com.sxbang.friday.model.reportCard;
import com.sxbang.friday.service.reportCardService;
import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "reportCard")
public class reportCardController {
    @Autowired(required = false)
    private reportCardService reportcardService;

    @RequestMapping(value = "{id}")
    @ResponseBody
    public Results<reportCard> searchReportCard(PageTableRequest request, reportCard reportcard, @PathVariable("id") String id) {
        request.countOffset();
        Map<String, Object> param = null;
        try {
            param = StrUtil.convertToMap(reportcard);
            param.put("testID",id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(id);
        return reportcardService.getByID(param, request.getOffset(), request.getLimit(),id);
    }
}
