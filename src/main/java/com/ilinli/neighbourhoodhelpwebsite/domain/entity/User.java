package com.ilinli.neighbourhoodhelpwebsite.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/***
 * @author : 马晓光
 * @date   : 2019/6/13
 * @description : 测试类entity
 **/

@TableName(value = "t_user")
@Data
public class User {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String name;

    private String email;

    private String passwd;

    private Integer status;
}
