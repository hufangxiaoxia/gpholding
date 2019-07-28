package com.gpholding.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gpholding.domain.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper extends BaseMapper<User> {

    @Select(" select count(*)  from t_user where email=#{email} and passwd=#{passwd} and status=0")
    int checkUser(@Param("email") String email, @Param("passwd") String  passwd);

    @Select(" select * from t_user where email=#{email}")
    User checkEmailIsExist(@Param("email") String email);

    @Update(" update  t_user  set code=#{code}  where email=#{email} and  status=0")
    int setEmailVerifyCode( @Param("email") String email , @Param("code") Integer code);


    @Update(" update  t_user  set passwd=#{passwd}  where email=#{email} and  code=#{code}  and  status=0")
    int updatePasswdByEmail( @Param("email") String email , @Param("passwd") String passwd, @Param("code") Integer code);
}
