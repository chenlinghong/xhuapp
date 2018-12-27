package com.app.util;



public class SetTimes {
    private static String typeStr;

    public static String setTypeTimes(int type){
        if(type==1){
            typeStr = "countryside_view_times";

        }else if(type==2){
            typeStr = "agriculture_view_times";
        }else if(type==3){
            typeStr = "agritainment_view_times";
        }else if(type==4){
            typeStr = "other_view_times";
        }else if(type==5){
            typeStr = "agriculture_products_times";
        }else if(type==6){
            typeStr = "agriculture_bp_times";
        }else if(type==7){
            typeStr = "create_delifood_times";
        }else if(type==8){
            typeStr = "delifood_commont_times";
        }else if(type==9){
            typeStr = "delifood_entertainment_times";
        }else if(type==10){
            typeStr = "farm_introduce_times";
        }else{
            typeStr = null;
        }
        return typeStr;
    }
}
