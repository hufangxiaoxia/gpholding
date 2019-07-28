package com.gpholding.service.impl;

import com.gpholding.dao.UserMapper;
import com.gpholding.domain.entity.User;
import com.gpholding.domain.vo.UserVo;
import com.gpholding.service.UserService;
import com.gpholding.domain.vo.ResponseResult;
import com.gpholding.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * @author : 马晓光
 * @date   : 2019/6/13
 * @description :
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseResult checkUser(UserVo vo) {
        String sign=MD5Util.sign(vo.getPasswd(),"mxg","UTF-8");
        int count  = userMapper.checkUser(vo.getEmail(),sign);
        if(count==1){
           return ResponseResult.success(null);
        }
        return ResponseResult.fail(null);
    }


    @Override
    public ResponseResult registerUser(UserVo vo) {
        //检查当前用户是否已经注册过
        if(vo == null ) {
            return ResponseResult.build(-1,"前端传递传递过来的对象为NULL，请检查",null);
        }
        if(StringUtils.isEmpty(vo.getEmail())){
            return ResponseResult.build(-1,"前端传递传递过来的email为空，请检查",null);
        }
        User user = userMapper.checkEmailIsExist(vo.getEmail());
        if(user!=null){
            if(user.getStatus()== 0){
                return ResponseResult.build(-1,"当前email已经注册过，不需要再次注册",null);
            }
            if(user.getStatus()== 1){
                return ResponseResult.build(-1,"当前email已经注册过，但是此人的状态为已删除",null);
            }
        }
        User o = new User();
        String passwd=MD5Util.sign(vo.getPasswd(),"mxg","UTF-8");
        o.setEmail(vo.getEmail());
        o.setPasswd(passwd);
        int count = userMapper.insert(o);
        if(count==1){
            return ResponseResult.success(null);
        }
        return ResponseResult.fail(null);
    }

    @Override
    public ResponseResult updatePasswdByEmail(UserVo vo) {
        String passwd=MD5Util.sign(vo.getPasswd(),"mxg","UTF-8");
        int count = userMapper.updatePasswdByEmail(vo.getEmail(),passwd,vo.getCode());
        if(count!=1){
            return ResponseResult.fail("更新密码失败");
        }
        return ResponseResult.success(null);
    }
}
