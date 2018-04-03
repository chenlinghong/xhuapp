package com.app.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @program: xhuapp
 * @description: 用户发布视频、语音、图文等动态相关实体类
 * @author: Mr.Chen
 * @create: 2018-04-02 15:42
 **/
@Component(value = "dynamic")
@Scope(value = "prototype")
public class Dynamic {

    private int dynamic_id;
    private String title;
    private String introduce;
    private String picture;
    private int user_id_f;

    public Dynamic(){}

    public int getDynamic_id() {
        return dynamic_id;
    }

    public void setDynamic_id(int dynamic_id) {
        this.dynamic_id = dynamic_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getUser_id_f() {
        return user_id_f;
    }

    public void setUser_id_f(int user_id_f) {
        this.user_id_f = user_id_f;
    }
}

