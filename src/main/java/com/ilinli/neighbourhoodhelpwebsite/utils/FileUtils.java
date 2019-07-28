package com.ilinli.neighbourhoodhelpwebsite.utils;

import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.util.Base64;

/**
 * 文件处理工具类
 *
 * @author ruoyi
 */
public class FileUtils {
    /**
     * 输出指定文件的byte数组
     *
     * @param filePath 文件路径
     * @param os       输出流
     * @return
     */
    public static void writeBytes(String filePath, OutputStream os) throws IOException {
        FileInputStream fis = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除文件
     *
     * @param filePath 文件
     * @return
     */
    public static boolean deleteFile(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
    /**
     * 获取文件名字(不包括扩展名)
     *
     * @param  文件
     * @return
     */
    public static String   getFileName(String fileName){
        if(StringUtils.isBlank(fileName)) {
            return null;
        }
        File file =new File(fileName);
        String fielName=file.getName().substring(0,file.getName().lastIndexOf("."));
        return  fielName;
    }

    /**
     * base64字符串转换成图片
     * @param imgStr		base64字符串
     * @param imgFilePath	图片存放路径
     * @return
     *
     * @author mxg
     * @dateTime 2018-02-23 14:42:17
     */
    public static boolean base64ToImage(String imgStr,String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片

        if (StringUtils.isEmpty(imgStr)) {
            return false;
        }
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            // Base64解码
            byte[] b = decoder.decode(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
