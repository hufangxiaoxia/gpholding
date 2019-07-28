package com.ilinli.neighbourhoodhelpwebsite.domain.vo;

import com.ilinli.neighbourhoodhelpwebsite.constants.IlinliStatic;
import lombok.Data;
import java.io.Serializable;

/**
 *
 * 返回结果
 * code  0-成功 -1失败
 * msg   提示信息
 * obj   返回数据结果
 */
@Data
public class ResponseResult implements Serializable{
    private Integer code;
    private Object  obj;
    private String  msg;

    public ResponseResult(){}

    public ResponseResult(Integer code,String msg ,Object obj){
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public static ResponseResult build(Integer code,String msg , Object obj) {
        return new ResponseResult(code, msg,obj);
    }

    /**
     * 成功
     **/
    public static ResponseResult success(Object obj) {
        return build(IlinliStatic.SUCCESS_CODE, IlinliStatic.SUCCESS_MESSAGE, obj);
    }
    /**
     * 失败
     *
     * */
    public static ResponseResult fail(Object obj) {
        return build(IlinliStatic.FAIL_CODE,IlinliStatic.FAIL_MESSAGE, obj);
    }
}
