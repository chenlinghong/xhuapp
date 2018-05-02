package com.app.util;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
 */

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UploadUtil {
    public static String upload(CommonsMultipartFile file, HttpServletRequest request,int flag){
        File targetFile = null;
        String fileName = file.getOriginalFilename();
        String msg = "";
        String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();
        String path = "";//文件存储位置

        String fileF = fileName.substring(fileName.lastIndexOf("."),fileName.length());     //文件后缀
        fileName = new Date().getTime() + "_" + new Random().nextInt(1000) + fileF;     //新的文件

        switch (flag){
            case 1: returnUrl =  returnUrl+"/upload/imgs/dynamic_images/";
                path = request.getSession().getServletContext().getRealPath("upload/imgs/dynamic_images/");break;
            case 2: returnUrl =  returnUrl+"/upload/videos/";
                path = request.getSession().getServletContext().getRealPath("upload/videos/");break;
            case 3: returnUrl =  returnUrl+"/upload/imgs/video_images/";
                path = request.getSession().getServletContext().getRealPath("upload/imgs/video_images/");break;
            default:break;
        }

        //先判断文件是否存在
        String fileAdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
        File file1 = new File(path + "/" + fileAdd);
        //如果文件夹不存在则创建
        if(!file1.exists() && !file1.isDirectory()){
            file1.mkdir();
        }
        targetFile = new File(file1,fileName);
        try {
            file.transferTo(targetFile);
            msg = returnUrl + fileAdd + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
