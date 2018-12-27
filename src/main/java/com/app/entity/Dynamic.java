package com.app.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 动态基本bean
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
    private String little_introduce;
    private int interest_id_f;

    public Dynamic(){}

    public int getInterest_id_f() {
        return interest_id_f;
    }

    public void setInterest_id_f(int interest_id_f) {
        this.interest_id_f = interest_id_f;
    }

    public String getLittle_introduce() {
        return little_introduce;
    }

    public void setLittle_introduce(String little_introduce) {
        this.little_introduce = little_introduce;
    }

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
