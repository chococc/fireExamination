package com.sxbang.friday.controller;

import com.sxbang.friday.base.result.PageTableRequest;
import com.sxbang.friday.model.LoggingEvent;
import io.swagger.annotations.ApiOperation;
import com.sxbang.friday.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.service.LoggingEventService;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

@Controller
@RequestMapping("/loggingEvents")
public class LoggingEventController {

    @Autowired
    private LoggingEventService loggingEventService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "保存")
    @ResponseBody
    public Results save(LoggingEvent loggingEvent) {
      return  loggingEventService.save(loggingEvent);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Results get(@PathVariable String id) {
        return loggingEventService.getById(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Results  update(LoggingEvent loggingEvent) {
       return loggingEventService.update(loggingEvent);
    }

    @GetMapping("/listPage")
    @ApiOperation(value = "列表")
    @ResponseBody
    public Results<LoggingEvent> list(PageTableRequest request, LoggingEvent loggingEvent) {
          request.countOffset();
          Map<String, Object> param = null;
          try {
              param =   StrUtil.convertToMap(loggingEvent);
          } catch (Exception e) {
              e.printStackTrace();
          }
        return loggingEventService.getByPage(param, request.getOffset(), request.getLimit());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除")
    @ResponseBody
    public Results delete(LoggingEvent loggingEvent) {
        return loggingEventService.delete( loggingEvent);
    }

   @ApiOperation(value = "编辑页面", notes = "跳转到菜单信息编辑页面")//描述
   @RequestMapping(value = "/addOrEdit", method = RequestMethod.GET)
    public ModelAndView roleEdit(Model model, HttpServletRequest request, LoggingEvent loggingEvent) {
        LoggingEvent newLoggingEvent = new LoggingEvent();
        if(!StringUtils.isEmpty(loggingEvent.getTimestmp())){
            newLoggingEvent = loggingEventService.getLoggingEventById(loggingEvent.getTimestmp());
        }
        model.addAttribute("loggingEvent",newLoggingEvent);
        ModelAndView modelAndView =  new ModelAndView("loggingEvent/addLoggingEvent");
        return modelAndView;
    }
}
