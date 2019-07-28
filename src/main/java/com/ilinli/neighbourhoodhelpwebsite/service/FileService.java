package com.ilinli.neighbourhoodhelpwebsite.service;

import java.io.InputStream;

public interface FileService {

    /**
     * 对图片文件进行压缩，压缩后的文件统一为扩展名*.jpg
     * @param  //源文件路径  输出文件路径
     * @return   true  压缩成功  false 压缩失败
     * @auth :
     * @date 2019/5/21
     *
     * **/
    boolean fileCompress(String sourceFileName,String destFileDir);
    boolean fileStreamCompress(InputStream inputStream, String destFileDir, double scale);
}
