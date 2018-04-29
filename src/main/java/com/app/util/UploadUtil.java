package com.app.util;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
 */

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class UploadUtil {
    public static String upload(CommonsMultipartFile file, HttpServletRequest request,int flag){
        String str = "";
        switch (flag){
            case 1:str =  request.getSession().getServletContext().getRealPath("upload/imgs/dynamic_images/");break;
            case 2:str =  request.getSession().getServletContext().getRealPath("upload/videos/");break;
            case 3:str =  request.getSession().getServletContext().getRealPath("upload/imgs/video_images/");break;
            default:break;
        }
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
