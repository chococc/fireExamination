package com.sxbang.friday.controller;

import com.sxbang.friday.base.result.ResponseCode;
import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.dto.UserDto;
import com.sxbang.friday.model.SysUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sxbang.friday.service.UserService;

@Controller
public class adduserController {
    @Autowired
    private UserService userService;
    @GetMapping(value = "/add")
    @ApiOperation(value = "用户新增页面", notes = "跳转到新增用户信息页面")//描述
    public String addUser(Model model) {
        model.addAttribute("sysUser",new SysUser());
        return "user/user-add";
    }

    @PostMapping(value = "/add")
    @ResponseBody
    @ApiOperation(value = "保存用户信息", notes = "保存新增的用户信息")//描述
    public Results<SysUser> saveUser(UserDto userDto, Integer roleId) {
        SysUser sysUser = null;
        sysUser = userService.getUser(userDto.getUsername());
        if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
            return Results.failure(ResponseCode.USERNAME_REPEAT.getCode(),ResponseCode.USERNAME_REPEAT.getMessage());
        }
        sysUser = userService.getUserByPhone(userDto.getTelephone());
        if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
            return Results.failure(ResponseCode.PHONE_REPEAT.getCode(),ResponseCode.PHONE_REPEAT.getMessage());
        }
        sysUser = userService.getUserByEmail(userDto.getEmail());
        if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
            return Results.failure(ResponseCode.EMAIL_REPEAT.getCode(),ResponseCode.EMAIL_REPEAT.getMessage());
        }

        userDto.setStatus(1);
        userDto.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        //userDto.setPassword(MD5.crypt(userDto.getPassword()));
        System.out.println(roleId);
        return userService.save(userDto,roleId);
    }

    @GetMapping(value = "/add/login")
    @ApiOperation(value = "用户新增页面", notes = "跳转到新增用户信息页面")//描述
    public String addUserLogin(Model model) {
        model.addAttribute("sysUser",new SysUser());
        return "user/user-add-login";
    }
    @PostMapping(value = "/add/login")
    @ResponseBody
    @ApiOperation(value = "保存用户信息", notes = "保存新增的用户信息")//描述
    public Results<SysUser> saveUserLogin(UserDto userDto, Integer roleId) {
        SysUser sysUser = null;
        sysUser = userService.getUser(userDto.getUsername());
        if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
            return Results.failure(ResponseCode.USERNAME_REPEAT.getCode(),ResponseCode.USERNAME_REPEAT.getMessage());
        }
        sysUser = userService.getUserByPhone(userDto.getTelephone());
        if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
            return Results.failure(ResponseCode.PHONE_REPEAT.getCode(),ResponseCode.PHONE_REPEAT.getMessage());
        }
        sysUser = userService.getUserByEmail(userDto.getEmail());
        if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
            return Results.failure(ResponseCode.EMAIL_REPEAT.getCode(),ResponseCode.EMAIL_REPEAT.getMessage());
        }

        userDto.setStatus(1);
        userDto.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        //userDto.setPassword(MD5.crypt(userDto.getPassword()));
        roleId=5;
        System.out.println(roleId);
        return userService.save(userDto,roleId);
    }
}
