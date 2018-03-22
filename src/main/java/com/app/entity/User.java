package com.app.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: xhuapp
 * @description: tb_user表所对应的实体类
 * @author: Mr.Chen
 * @create: 2018-03-22 19:38
 **/
@Component(value = "user")
public class User {

    private int user_id;     //用户ID
    private String username;        //用户姓名
    private String telphone;        //用户电话号码
    private Date birthday;      //用户出生日期
    private String head_portrail;       //用户头像图片路径
    private char gender;        //用户性别
    private String password;        //用户密码
    private String introduce;       //用户备注

    public User() {

    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getTelphone() {
        return telphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getHead_portrail() {
        return head_portrail;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public char getGender() {
        return gender;
    }

    public void setHead_portrail(String head_portrail) {
        this.head_portrail = head_portrail;
    }


}
