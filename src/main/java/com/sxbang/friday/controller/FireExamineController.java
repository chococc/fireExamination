package com.sxbang.friday.controller;

import com.sxbang.friday.base.result.PageTableRequest;
import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.model.FireExamine;
import com.sxbang.friday.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import com.sxbang.friday.service.FireExamineService;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

@Controller
@RequestMapping("/fireExamines")
@Log
public class FireExamineController {

    @Autowired
    private FireExamineService fireExamineService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "保存")
    @ResponseBody
    public Results save(@RequestBody FireExamine fireExamine) {
        log.info("插入考试："+fireExamine.toString());
      return  fireExamineService.save(fireExamine);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Results get(@PathVariable Integer id) {
        return fireExamineService.getById(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Results  update(FireExamine fireExamine) {
       return fireExamineService.update(fireExamine);
    }

    @GetMapping("/listPage")
    @ApiOperation(value = "列表")
    @ResponseBody
    public Results<FireExamine> list(PageTableRequest request, FireExamine fireExamine) {
          request.countOffset();
          Map<String, Object> param = null;
          try {
              param =   StrUtil.convertToMap(fireExamine);
          } catch (Exception e) {
              e.printStackTrace();
          }
        return fireExamineService.getByPage(param, request.getOffset(), request.getLimit());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除")
    @ResponseBody
    public Results delete(FireExamine fireExamine) {
        return fireExamineService.delete( fireExamine);
    }

   @ApiOperation(value = "编辑页面", notes = "跳转到菜单信息编辑页面")//描述
   @RequestMapping(value = "/addOrEdit", method = RequestMethod.GET)
    public ModelAndView roleEdit(Model model, HttpServletRequest request, FireExamine fireExamine) {
        FireExamine newFireExamine = new FireExamine();
        if(0 != fireExamine.getExamineId()){
            newFireExamine = fireExamineService.getFireExamineById(fireExamine.getExamineId());
        }else {
            newFireExamine.setExamineId(0);
        }
        model.addAttribute("fireExamine",newFireExamine);
        ModelAndView modelAndView =  new ModelAndView("fireExamine/addFireExamine");
        return modelAndView;
    }
}
