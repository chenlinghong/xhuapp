package com.app.dto;

import com.app.entity.Farm;

/**
 * @program: xhuapp
 * @description: 农场相关Dto
 * @author: Mr.Chen
 * @create: 2018-04-23 21:11
 **/
public class FarmDto {

    private int code;       //返回码
    private String message;     //状态返回码说明
    private Farm farm;

    public FarmDto(){

    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }
}
