package com.sxbang.friday.controller;

import com.sxbang.friday.base.result.PageTableRequest;
import com.sxbang.friday.model.FireLevel;
import io.swagger.annotations.ApiOperation;
import com.sxbang.friday.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.service.FireLevelService;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/fireLevels")
public class FireLevelController {

    @Autowired
    private FireLevelService fireLevelService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "保存")
    @ResponseBody
    public Results save(FireLevel fireLevel) {
      return  fireLevelService.save(fireLevel);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Results get(@PathVariable Integer id) {
        return fireLevelService.getById(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Results  update(FireLevel fireLevel) {
       return fireLevelService.update(fireLevel);
    }

    @GetMapping("/listPage")
    @ApiOperation(value = "列表")
    @ResponseBody
    public Results<FireLevel> list(PageTableRequest request, FireLevel fireLevel) {
          request.countOffset();
          Map<String, Object> param = null;
          try {
              param =   StrUtil.convertToMap(fireLevel);
          } catch (Exception e) {
              e.printStackTrace();
          }
        return fireLevelService.getByPage(param, request.getOffset(), request.getLimit());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除")
    @ResponseBody
    public Results delete(FireLevel fireLevel) {
        return fireLevelService.delete( fireLevel);
    }

   @ApiOperation(value = "编辑页面", notes = "跳转到菜单信息编辑页面")//描述
   @RequestMapping(value = "/addOrEdit", method = RequestMethod.GET)
    public ModelAndView roleEdit(Model model, HttpServletRequest request, FireLevel fireLevel) {
        FireLevel newFireLevel = new FireLevel();
        if(0 != fireLevel.getLevelId()){
            newFireLevel = fireLevelService.getFireLevelById(fireLevel.getLevelId());
        }
        model.addAttribute("fireLevel",newFireLevel);
        ModelAndView modelAndView =  new ModelAndView("fireLevel/addFireLevel");
        return modelAndView;
    }

    @ApiOperation(value = "编辑页面", notes = "跳转到菜单信息编辑页面")//描述
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Results<FireLevel> list() {
        Results<FireLevel>  fireLevelResults= fireLevelService.getByPage(new HashMap<>(),0,1000);
        return fireLevelResults;
    }
}
