package com.gpholding.controller;

import com.gpholding.domain.vo.ResponseResult;
import com.gpholding.domain.vo.UserVo;
import com.gpholding.service.EmailService;
import com.gpholding.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/***
 * @author : 马晓光
 * @date   : 2019/6/13
 * @description :
 **/
@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseResult userLogin(@RequestBody UserVo userVo){
       log.info("登录请求参数: 用户名 {} , 密码 {}",userVo.getEmail(),userVo.getPasswd());
       return userService.checkUser(userVo);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseResult registerLogin(@RequestBody UserVo userVo){
        log.info("用户注册请求参数: 用户名 {} , 密码 {}",userVo.getEmail(),userVo.getPasswd());
        return userService.registerUser(userVo);
    }
    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/mail/{toMail}",method = RequestMethod.GET)
    public ResponseResult sendMail(@PathVariable String toMail){
        log.info("给{}发送邮件",toMail);
        return    emailService.sendMail(toMail);
    }

    @RequestMapping(value = "/update-passwd",method = RequestMethod.POST)
    public ResponseResult updatePasswd(@RequestBody UserVo userVo){
        log.info("用户更新密码请求参数: 用户名 {} , 密码 {} 验证码 {}",userVo.getEmail(),userVo.getPasswd(),userVo.getCode());
        return userService.updatePasswdByEmail(userVo);
    }
}
