package com.app.vo;/*
 *@Author:dxlin
 *@Description：用于动态的接口
 *@Date: 2018-3-23
 */

import com.app.entity.Dynamic;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "dynamicApiVo")
public class DynamicApiVo {
    private Dynamic dynamic;
    private List<Dynamic> dynamicList;
    private int code;
    private String message;

    public DynamicApiVo(){}


    public List<Dynamic> getDynamicList() {
        return dynamicList;
    }

    public void setDynamicList(List<Dynamic> dynamicList) {
        this.dynamicList = dynamicList;
    }

    public Dynamic getDynamic() {
        return dynamic;
    }

    public void setDynamic(Dynamic dynamic) {
        this.dynamic = dynamic;
    }

    public int getCode() {
        return code;
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
}
