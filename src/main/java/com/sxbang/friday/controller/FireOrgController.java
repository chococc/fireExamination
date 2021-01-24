package com.sxbang.friday.controller;

import com.sxbang.friday.base.result.PageTableRequest;
import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.model.FireLevel;
import com.sxbang.friday.model.FireOrg;
import com.sxbang.friday.service.FireOrgService;
import com.sxbang.friday.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/fireOrgs")
public class FireOrgController {

    @Autowired
    private FireOrgService fireOrgService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "保存")
    @ResponseBody
    public Results save(FireOrg fireOrg) {
      return  fireOrgService.save(fireOrg);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Results get(@PathVariable Integer id) {
        return fireOrgService.getById(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Results  update(FireOrg fireOrg) {
       return fireOrgService.update(fireOrg);
    }

    @GetMapping("/listPage")
    @ApiOperation(value = "列表")
    @ResponseBody
    public Results<FireOrg> list(PageTableRequest request, FireOrg fireOrg) {
          request.countOffset();
          Map<String, Object> param = null;
          try {
              param =   StrUtil.convertToMap(fireOrg);
          } catch (Exception e) {
              e.printStackTrace();
          }
          System.out.println(fireOrgService.getByPage(param, request.getOffset(), request.getLimit()));
        return fireOrgService.getByPage(param, request.getOffset(), request.getLimit());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除")
    @ResponseBody
    public Results delete(FireOrg fireOrg) {
        return fireOrgService.delete( fireOrg);
    }

   @ApiOperation(value = "编辑页面", notes = "跳转到菜单信息编辑页面")//描述
   @RequestMapping(value = "/addOrEdit", method = RequestMethod.GET)
    public ModelAndView roleEdit(Model model, HttpServletRequest request, FireOrg fireOrg) {
        FireOrg newFireOrg = new FireOrg();
        if(0 != fireOrg.getOrgId()){
            newFireOrg = fireOrgService.getFireOrgById(fireOrg.getOrgId());
        }
        model.addAttribute("fireOrg",newFireOrg);
        ModelAndView modelAndView =  new ModelAndView("fireOrg/addFireOrg");
        return modelAndView;
    }

    /**
     * 查询列表
     * @return
     */
    @ApiOperation(value = "编辑页面", notes = "跳转到菜单信息编辑页面")//描述
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Results<FireOrg> list() {
        Results<FireOrg>  fireLevelResults= fireOrgService.getByPage(new HashMap<>(),0,1000);
        return fireLevelResults;
    }

}
