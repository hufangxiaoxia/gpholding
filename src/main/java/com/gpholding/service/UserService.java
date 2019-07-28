package com.gpholding.service;

import com.gpholding.domain.vo.UserVo;
import com.gpholding.domain.vo.ResponseResult;

public interface UserService {
     ResponseResult checkUser(UserVo vo);

     ResponseResult registerUser(UserVo vo);

     ResponseResult updatePasswdByEmail(UserVo vo);
}
