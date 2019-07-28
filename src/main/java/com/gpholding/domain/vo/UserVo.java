package com.gpholding.domain.vo;
import lombok.Data;
/***
 * @author : 马晓光
 * @date   : 2019/7/27
 * @description :
 **/
@Data
public class UserVo {
    private String email;
    private String passwd;
    private Integer code;
}
