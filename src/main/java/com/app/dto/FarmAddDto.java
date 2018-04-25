package com.app.dto;

/**
 * @program: xhuapp
 * @description: 新增农场接收参数用DTO
 * @author: Mr.Chen
 * @create: 2018-04-25 14:32
 **/
public class FarmAddDto {

    private String name;        //农场名称
    private String longitude;       //经度
    private String latitude;        //纬度
    private String location_name;   //位置名称
    private int user_id;        //用户ID
    private String introduce;       //农场介绍
    private int area;       //农场面积

    public FarmAddDto(){

    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getName() {
        return name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
