package com.app.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 通过用户ID查询用户基本信息
 *
 * @param user_id
 * @return 用户信息
 * @throws Exception
 */
@Component(value = "dynamic")
@Scope(value = "prototype")
public class Dynamic {
    private int dynamic_id;
    private String title;
    private String introduce;
    private String picture;
    private int user_id_f;
    private boolean dynamic_type;
    private String address;
    private int prize;
    private int look_persons;

    public Dynamic(){}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public int getLook_persons() {
        return look_persons;
    }

    public void setLook_persons(int look_persons) {
        this.look_persons = look_persons;
    }

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

    public boolean isDynamic_type() {
        return dynamic_type;
    }

    public void setDynamic_type(boolean dynamic_type) {
        this.dynamic_type = dynamic_type;
    }


}
