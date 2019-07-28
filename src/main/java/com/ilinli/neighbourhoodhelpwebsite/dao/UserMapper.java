package com.ilinli.neighbourhoodhelpwebsite.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ilinli.neighbourhoodhelpwebsite.domain.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {

    @Select(" select count(*)  from t_user where email=#{email} and passwd=#{passwd} and status=0")
    int checkUser(@Param("email") String email, @Param("passwd") String  passwd);

    @Select(" select * from t_user where email=#{email}")
    User checkEmailIsExist(@Param("email") String email);
}
