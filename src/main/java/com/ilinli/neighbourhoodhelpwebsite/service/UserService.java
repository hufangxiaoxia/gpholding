package com.ilinli.neighbourhoodhelpwebsite.service;

import com.ilinli.neighbourhoodhelpwebsite.domain.entity.Test;
import com.ilinli.neighbourhoodhelpwebsite.domain.vo.ResponseResult;
import com.ilinli.neighbourhoodhelpwebsite.domain.vo.UserVo;

import java.util.List;

public interface UserService {
     ResponseResult checkUser(UserVo vo);

     ResponseResult registerUser(UserVo vo);
}
