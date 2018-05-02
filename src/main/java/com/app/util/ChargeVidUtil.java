package com.app.util;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
 */

public class ChargeVidUtil {
    public static boolean isVideo(String videostr){
        int videoTypeInt = videostr.lastIndexOf(".");
        String videoTypeStr = videostr.substring(videoTypeInt);
        if(!videoTypeStr.equals(".avi")&&!videoTypeStr.equals(".rmvb")&&!videoTypeStr.equals(".rm")
                &&!videoTypeStr.equals(".asf")&&!videoTypeStr.equals(".divx")&&!videoTypeStr.equals(".mpg")&&!videoTypeStr.equals(".mpeg")
                &&!videoTypeStr.equals(".mpe")&&!videoTypeStr.equals(".wmv")&&!videoTypeStr.equals(".mp4")&&!videoTypeStr.equals(".mkv")
                &&!videoTypeStr.equals(".vob")){
            return false;
        }else {
            return true;
        }
    }
}
