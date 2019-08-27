package com.yaspeed.core.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 通用文件下载(模板,中间文件)
 * @author Zy
 * @date 2019年6月19日17:55:04
 */
public class FileDownload {
    public void fileDownload(String path, String fileName, HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="+ fileName);
        try {
            InputStream inputStream = new FileInputStream(new File(path+ File.separator + fileName));
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
