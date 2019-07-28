package com.ilinli.neighbourhoodhelpwebsite.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 马晓光 on 2019/7/26
 */
@Slf4j
public class ResponseUtil {

    public static void resonse(HttpServletResponse response, Object o){
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.append(JSON.toJSONString(o));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}
