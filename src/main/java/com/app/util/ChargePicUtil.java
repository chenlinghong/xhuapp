package com.app.util;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
 */

public class ChargePicUtil {
    public static boolean ispic(String picstr){
        int imgTypeint = picstr.lastIndexOf(".");
        String imgtypestr = picstr.substring(imgTypeint);
        if(!imgtypestr.equals(".jpg")&&!imgtypestr.equals(".png")&&!imgtypestr.equals(".gif")){
            return false;
        }else {
            return true;
        }
    }
}
