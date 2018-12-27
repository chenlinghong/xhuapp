package com.app.entity;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
 */

/**
 * 评论的bean
 * 用于评论的基本类
 */

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "commont")
@Scope(value = "prototype")
public class Commont {
    private int commont_id;
    private int user_id_f;
    private String commont_body;
    private int father_commont_id;

    public Commont(){}

    public int getCommont_id() {
        return commont_id;
    }

    public void setCommont_id(int commont_id) {
        this.commont_id = commont_id;
    }

    public int getUser_id_f() {
        return user_id_f;
    }

    public void setUser_id_f(int user_id_f) {
        this.user_id_f = user_id_f;
    }

    public String getCommont_body() {
        return commont_body;
    }

    public void setCommont_body(String commont_body) {
        this.commont_body = commont_body;
    }

    public int getFather_commont_id() {
        return father_commont_id;
    }

    public void setFather_commont_id(int father_commont_id) {
        this.father_commont_id = father_commont_id;
    }


}
