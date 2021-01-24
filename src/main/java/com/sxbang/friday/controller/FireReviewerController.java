package com.sxbang.friday.controller;

import com.sxbang.friday.base.result.PageTableRequest;
import com.sxbang.friday.model.FireReviewer;
import io.swagger.annotations.ApiOperation;
import com.sxbang.friday.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.service.FireReviewerService;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

@Controller
@RequestMapping("/fireReviewers")
public class FireReviewerController {

    @Autowired
    private FireReviewerService fireReviewerService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "保存")
    @ResponseBody
    public Results save(FireReviewer fireReviewer) {
      return  fireReviewerService.save(fireReviewer);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Results get(@PathVariable Integer id) {
        return fireReviewerService.getById(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Results  update(FireReviewer fireReviewer) {
       return fireReviewerService.update(fireReviewer);
    }

    @GetMapping("/listPage")
    @ApiOperation(value = "列表")
    @ResponseBody
    public Results<FireReviewer> list(PageTableRequest request, FireReviewer fireReviewer) {
          request.countOffset();
          Map<String, Object> param = null;
          try {
              param =   StrUtil.convertToMap(fireReviewer);
          } catch (Exception e) {
              e.printStackTrace();
          }
        return fireReviewerService.getByPage(param, request.getOffset(), request.getLimit());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除")
    @ResponseBody
    public Results delete(FireReviewer fireReviewer) {
        return fireReviewerService.delete( fireReviewer);
    }

   @ApiOperation(value = "编辑页面", notes = "跳转到菜单信息编辑页面")//描述
   @RequestMapping(value = "/addOrEdit", method = RequestMethod.GET)
    public ModelAndView roleEdit(Model model, HttpServletRequest request, FireReviewer fireReviewer) {
        FireReviewer newFireReviewer = new FireReviewer();
        if(0 != fireReviewer.getReviewerId()){
            newFireReviewer = fireReviewerService.getFireReviewerById(fireReviewer.getReviewerId());
        }
        model.addAttribute("fireReviewer",newFireReviewer);
        ModelAndView modelAndView =  new ModelAndView("fireReviewer/addFireReviewer");
        return modelAndView;
    }
}
