package com.app.dto;

import java.util.Date;

/**
 * @program: xhuapp
 * @description: 用户基本信息返回Dto
 * @author: Mr.Chen
 * @create: 2018-05-06 16:55
 **/
public class UserDto {

    private int user_id;     //用户ID
    private String username;        //用户姓名
    private String telphone;        //用户电话号码
    private Date birthday;      //用户出生日期
    private String head_portrail;       //用户头像图片路径
    private char gender;        //用户性别
    private String password;        //用户密码
    private String introduce;       //用户备注
    private String longitude;       //经度
    private String latitude;        //纬度
    private int fans;       //粉丝
    private int attention;      //关注

    public UserDto(){

    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public void setAttention(int attention) {
        this.attention = attention;
    }

    public int getFans() {
        return fans;
    }

    public int getAttention() {
        return attention;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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
