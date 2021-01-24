package com.sxbang.friday.controller;

import com.sxbang.friday.base.result.PageTableRequest;
import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.excel.ExcelUtil;
import com.sxbang.friday.excel.ReadExcelUtils;
import com.sxbang.friday.model.FireMan;
import com.sxbang.friday.service.FireManService;
import com.sxbang.friday.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/fireMans")
public class FireManController {

    @Autowired
    private FireManService fireManService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "保存")
    @ResponseBody
    public Results save(FireMan fireMan) {
      return  fireManService.save(fireMan);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Results get(@PathVariable Integer id) {
        return fireManService.getById(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Results  update(FireMan fireMan) {
       return fireManService.update(fireMan);
    }

    @GetMapping("/listPage")
    @ApiOperation(value = "列表")
    @ResponseBody
    public Results<FireMan> list(PageTableRequest request, FireMan fireMan) {
          request.countOffset();
          Map<String, Object> param = null;
          try {
              param =   StrUtil.convertToMap(fireMan);
          } catch (Exception e) {
              e.printStackTrace();
          }
        return fireManService.getByPage(param, request.getOffset(), request.getLimit());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除")
    @ResponseBody
    public Results delete(FireMan fireMan) {
        return fireManService.delete( fireMan);
    }

   @ApiOperation(value = "编辑页面", notes = "跳转到菜单信息编辑页面")//描述
   @RequestMapping(value = "/addOrEdit", method = RequestMethod.GET)
    public ModelAndView roleEdit(Model model, HttpServletRequest request, FireMan fireMan) {
        FireMan newFireMan = new FireMan();
        if(0 != fireMan.getExamineUserid()){
            newFireMan = fireManService.getFireManById(fireMan.getExamineUserid());
        }
        model.addAttribute("fireMan",newFireMan);
        ModelAndView modelAndView =  new ModelAndView("fireMan/addFireMan");
        return modelAndView;
    }


    @RequestMapping(value="/importUser",method = RequestMethod.POST)
    @ResponseBody
    public List importUser(@RequestParam MultipartFile file) throws IOException {
        List<Map<Integer, String>> list = ExcelUtil.excelToListMap(file.getInputStream()) ;
        fireManService.importUser(list);
        return list;
    }
}
