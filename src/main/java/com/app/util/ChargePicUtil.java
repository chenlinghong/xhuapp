package com.app.util;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
 */

public class ChargePicUtil {
    public static boolean ispic(String picstr){
        String[] picstrs = picstr.split("\\.");
        if(picstrs.length==0||!picstrs[1].equals("jpg")&&!picstrs[1].equals("png")&&!picstrs[1].equals("gif")){
            return false;
        }else {
            return true;
        }
    }
}
