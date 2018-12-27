package com.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateInterestIdUtil {

    private int target;

    public static String returnInterestId(int user_id_f){
        String dateStr = new SimpleDateFormat("yyyMMddHHmm").format(new Date())+user_id_f;
        return dateStr;
    }

}
