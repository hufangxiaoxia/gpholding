package com.ilinli.neighbourhoodhelpwebsite.controller;

import com.ilinli.neighbourhoodhelpwebsite.domain.vo.ResponseResult;
import com.ilinli.neighbourhoodhelpwebsite.domain.vo.UserVo;
import com.ilinli.neighbourhoodhelpwebsite.service.EmailService;
import com.ilinli.neighbourhoodhelpwebsite.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/mail",method = RequestMethod.GET)
    public ResponseResult sendMail(){
        emailService.sendMail();
        return null;
    }
}
