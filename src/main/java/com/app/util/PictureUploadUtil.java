package com.app.util;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
 */

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class PictureUploadUtil {
    public static String pictureupload(CommonsMultipartFile file, HttpServletRequest request){
        String str =  request.getSession().getServletContext().getRealPath("upload/imgs/");
        String filepath = str+file.getOriginalFilename();
        try {
            file.transferTo(new File(filepath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return filepath;
    }
}
