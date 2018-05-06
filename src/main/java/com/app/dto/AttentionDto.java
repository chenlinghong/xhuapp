package com.app.dto;

import java.util.ArrayList;

/**
 * @program: xhuapp
 * @description:
 * @author: Mr.Chen
 * @create: 2018-05-06 17:27
 **/
public class AttentionDto {

    private int code;
    private String message;
    private ArrayList<Integer> data;

    public AttentionDto(){

    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public ArrayList<Integer> getData() {
        return data;
    }

    public void setData(ArrayList<Integer> data) {
        this.data = data;
    }
}
