package com.sxbang.friday.controller;

import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.dao.FireOrderDao;
import com.sxbang.friday.model.FireExamine;
import com.sxbang.friday.model.FireLevel;
import com.sxbang.friday.model.FireOrder;
import com.sxbang.friday.service.FireExamineService;
import com.sxbang.friday.service.StudentPayService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * 学生预约考生
 * @author ：laijinrong
 * @date ：Created in 2020/5/17 21:13
 * @modified By：
 */
@Controller
@RequestMapping("/studentPays")
public class StudentPayController {

    @Autowired
    FireOrderDao fireOrderDao;

    @Autowired
    private FireExamineService fireExamineService;


    @Autowired
    StudentPayService studentPayService;

    /**
     *  查询考生可以预约的列表
     */
    @ApiOperation(value = "编辑页面", notes = "跳转到菜单信息编辑页面")//描述
    @RequestMapping(value = "/examinePagelist", method = RequestMethod.GET)
    @ResponseBody
    public Results<FireExamine> list() {
        Results<FireExamine>  fireLevelResults= fireExamineService.getByPage(new HashMap<>(),0,1000);
        return fireLevelResults;
    }



    /**
     *  查询考生可以预约的列表
     */
    @ApiOperation(value = "预约考试", notes = "跳转到菜单信息编辑页面")//描述
    @RequestMapping(value = "/orderExamine/{examineId}")
    @ResponseBody
    public Results<FireOrder> orderExamine(@PathVariable(value = "examineId") Integer examineId) throws InterruptedException {
        Results<FireOrder> fireOrder= studentPayService.orderExamine(examineId);
        return fireOrder;
    }


    /**
     *   支付考试
     */
    @ApiOperation(value = "支付", notes = "跳转到菜单信息编辑页面")//描述
    @RequestMapping(value = "/payExamine/{orderId}")
    @ResponseBody
    public Results<FireOrder>  payExamine( @PathVariable(value = "orderId") Integer orderId) {
        Results<FireOrder> fireOrder= studentPayService.payExamine(orderId);
        return fireOrder;
    }


    @ApiOperation(value = "支付", notes = "跳转到菜单信息编辑页面")//描述
    @RequestMapping(value = "/paypage/{orderId}", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView  payExamine(Model model,@PathVariable(value = "orderId") Integer orderId) {

        FireOrder  order= fireOrderDao.getById(orderId);
        model.addAttribute("fireOrder",order);
        ModelAndView modelAndView =  new ModelAndView("studentPay/orderPay");
        return modelAndView;
    }

    @ApiOperation(value="支付-demo",notes="支付测试用页面")
    @RequestMapping(value="/paypage",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView GetPayPage(){
        ModelAndView modelAndView=new ModelAndView("studentPay/orderPay");
        return modelAndView;
    }










}
