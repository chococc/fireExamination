package com.sxbang.friday.controller;

import com.sxbang.friday.base.result.PageTableRequest;
import com.sxbang.friday.model.FireOrder;
import io.swagger.annotations.ApiOperation;
import com.sxbang.friday.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.service.FireOrderService;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

@Controller
@RequestMapping("/fireOrders")
public class FireOrderController {

    @Autowired
    private FireOrderService fireOrderService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "保存")
    @ResponseBody
    public Results save(FireOrder fireOrder) {
      return  fireOrderService.save(fireOrder);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Results get(@PathVariable Integer id) {
        return fireOrderService.getById(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Results  update(FireOrder fireOrder) {
       return fireOrderService.update(fireOrder);
    }

    @GetMapping("/listPage")
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

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除")
    @ResponseBody
    public Results delete(FireOrder fireOrder) {
        return fireOrderService.delete( fireOrder);
    }

   @ApiOperation(value = "编辑页面", notes = "跳转到菜单信息编辑页面")//描述
   @RequestMapping(value = "/addOrEdit", method = RequestMethod.GET)
    public ModelAndView roleEdit(Model model, HttpServletRequest request, FireOrder fireOrder) {
        FireOrder newFireOrder = new FireOrder();
        if(0 != fireOrder.getOrderId()){
            newFireOrder = fireOrderService.getFireOrderById(fireOrder.getOrderId());
        }
        model.addAttribute("fireOrder",newFireOrder);
        ModelAndView modelAndView =  new ModelAndView("fireOrder/addFireOrder");
        return modelAndView;
    }
}
