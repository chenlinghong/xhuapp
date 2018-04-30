package com.app.util;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
 */

public class ChargeVidUtil {
    public static boolean isVideo(String videostr){
        String[] picstrs = videostr.split("\\.");
        if(picstrs.length==0||!picstrs[1].equals("avi")&&!picstrs[1].equals("rmvb")&&!picstrs[1].equals("rm")
                &&!picstrs[1].equals("asf")&&!picstrs[1].equals("divx")&&!picstrs[1].equals("mpg")&&!picstrs[1].equals("mpeg")
                &&!picstrs[1].equals("mpe")&&!picstrs[1].equals("wmv")&&!picstrs[1].equals("mp4")&&!picstrs[1].equals("mkv")
                &&!picstrs[1].equals("vob")){
            return false;
        }else {
            return true;
        }
    }
}
