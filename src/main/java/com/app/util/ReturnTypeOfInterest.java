package com.app.util;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
 */

import com.app.entity.Interest;

public class ReturnTypeOfInterest {
    private static int type;

    public static int getType(Interest interest){
        if(interest.getAgriculture_view()==1){
            type = 1;

        }else if(interest.getAgriculture_products()==1){
            type = 2;
        }else if(interest.getAgriculture_bp()==1){
            type = 3;
        }else if(interest.getOther_view()==1){
            type = 4;
        }else if(interest.getCreate_delifood()==1){
            type = 5;
        }else if(interest.getDelifood_commont()==1){
            type = 6;
        }else if(interest.getFarm_introduce()==1){
            type = 7;
        }else if(interest.getDelifood_entertainment()==1){
            type = 8;
        }else if(interest.getAgritainment_view()==1){
            type = 9;
        }else if(interest.getCountryside_view()==1){
            type = 10;
        }else{
            type = 0;
        }

        return type;
    }
}
