package com.sxbang.friday.controller;

import com.sxbang.friday.base.result.PageTableRequest;
import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.dao.FireOrderDao;
import com.sxbang.friday.model.FireOrder;
import com.sxbang.friday.service.FireOrderService;
import com.sxbang.friday.service.StudentPayService;
import com.sxbang.friday.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 学生预约考生
 */
@Controller
@RequestMapping("/studentOrder")
public class StudentOrderController {

    @Autowired
    private FireOrderService fireOrderService;

    @Autowired
    private StudentPayService studentPayService;

    @Autowired
    FireOrderDao fireOrderDao;
    /**
     * 考生已经预约的考试
     * @param request
     * @param fireOrder
     * @return
     */
    @GetMapping("/orderdList")
    @ApiOperation(value = "列表")
    @ResponseBody
    public Results<FireOrder> list(PageTableRequest request, FireOrder fireOrder) {
        request.countOffset();
        Map<String, Object> param = null;
        try {
            param =   StrUtil.convertToMap(fireOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fireOrderService.getByPage(param, request.getOffset(), request.getLimit());
    }


    /**
     * 开始考试
     * @return
     */
    @GetMapping("/startExamine/{orderId}")
    @ApiOperation(value = "列表")
    @ResponseBody
    public Results<FireOrder> startExamine(@PathVariable(value = "orderId")Integer orderId) {
        Results<FireOrder>  orderResults= studentPayService.startExamine(orderId);
        return orderResults;
    }


    /**
     * 结束考试
     * @return
     */
    @PostMapping("/finishExamine/{orderId}")
    @ApiOperation(value = "列表")
    @ResponseBody
    public Results<FireOrder> finishExamine(@PathVariable(value = "orderId")Integer orderId) {
        Results<FireOrder>  orderResults= studentPayService.startExamine(orderId);
        return orderResults;
    }

    @ApiOperation(value = "考试页面", notes = "跳转到菜单信息编辑页面")//描述
    @RequestMapping(value = "/examinePage/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView  examinePage(Model model,@PathVariable(value = "orderId") Integer orderId) {

        FireOrder  order= fireOrderDao.getById(orderId);
        model.addAttribute("fireOrder",order);
        ModelAndView modelAndView =  new ModelAndView("studentOrder/examinePage");
        return modelAndView;
    }

}
