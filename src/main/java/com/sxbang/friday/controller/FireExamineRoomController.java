package com.sxbang.friday.controller;

import com.sxbang.friday.base.result.PageTableRequest;
import com.sxbang.friday.model.FireExamineRoom;
import com.sxbang.friday.model.FireLevel;
import com.sxbang.friday.model.SysUser;
import io.swagger.annotations.ApiOperation;
import com.sxbang.friday.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.service.FireExamineRoomService;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/fireExamineRooms")
public class FireExamineRoomController {

    @Autowired
    private FireExamineRoomService fireExamineRoomService;


    @GetMapping(value = "/addpage")
    @ApiOperation(value = "新增场地页面", notes = "跳转到新增场地页面")//描述
    public ModelAndView addUser(Model model) {
        model.addAttribute("fireExamineRoom",new FireExamineRoom());
        ModelAndView modelAndView = new ModelAndView("fireMan/rolemanage/addFireExamineRoom");
        return modelAndView;
    }



    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "保存")
    @ResponseBody
    public Results save(FireExamineRoom fireExamineRoom) {
      return  fireExamineRoomService.save(fireExamineRoom);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Results get(@PathVariable Integer id) {
        return fireExamineRoomService.getById(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Results  update(FireExamineRoom fireExamineRoom) {
       return fireExamineRoomService.update(fireExamineRoom);
    }




    @GetMapping("/listPage")
    @ApiOperation(value = "列表")
    @ResponseBody
    public Results<FireExamineRoom> list(PageTableRequest request, FireExamineRoom fireExamineRoom) {
          request.countOffset();
          Map<String, Object> param = null;
          try {
              param =   StrUtil.convertToMap(fireExamineRoom);
          } catch (Exception e) {
              e.printStackTrace();
          }
        return fireExamineRoomService.getByPage(param, request.getOffset(), request.getLimit());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除")
    @ResponseBody
    public Results delete(FireExamineRoom fireExamineRoom) {
        return fireExamineRoomService.delete( fireExamineRoom);
    }



   @ApiOperation(value = "编辑页面", notes = "跳转到菜单信息编辑页面")//描述
   @RequestMapping(value = "/addOrEdit", method = RequestMethod.GET)
    public ModelAndView roleEdit(Model model, HttpServletRequest request, FireExamineRoom fireExamineRoom) {
        FireExamineRoom newFireExamineRoom = new FireExamineRoom();
        if(0 != fireExamineRoom.getExamineRoomId()){
            newFireExamineRoom = fireExamineRoomService.getFireExamineRoomById(fireExamineRoom.getExamineRoomId());
        }
        model.addAttribute("fireExamineRoom",newFireExamineRoom);
        ModelAndView modelAndView =  new ModelAndView("fireMan/rolemanage/addFireExamineRoom");
        return modelAndView;
    }

    @ApiOperation(value = "编辑页面", notes = "跳转到菜单信息编辑页面")//描述
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Results<FireExamineRoom> list() {
        Results<FireExamineRoom>  fireLevelResults= fireExamineRoomService.getByPage(new HashMap<>(),0,1000);
        return fireLevelResults;
    }


}
