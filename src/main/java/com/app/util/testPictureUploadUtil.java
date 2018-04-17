package com.app.util;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
 */

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class testPictureUploadUtil {
    public static String pictureupload(CommonsMultipartFile[] files, HttpServletRequest request){
        String str =  request.getSession().getServletContext().getRealPath("upload/imgs/");
        String filepaths = "";
        for(CommonsMultipartFile file:files){
            String filepath = str+file.getOriginalFilename();
            filepaths = filepath+filepath+";";
            try {
                file.transferTo(new File(filepath));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return filepaths;
    }
}
