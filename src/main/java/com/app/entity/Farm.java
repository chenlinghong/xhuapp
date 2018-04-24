package com.app.entity;

/**
 * @program: xhuapp
 * @description: 农场实体类
 * @author: Mr.Chen
 * @create: 2018-04-23 19:23
 **/
public class Farm {

    private int farm_id;        //农场ID
    private String name;        //农场名称
    private String longitude;       //经度
    private String latitude;        //纬度
    private String location_name;       //位置名称
    private int user_id;        //用户ＩＤ
    private String introduce;       //农场介绍
    private String photo;       //农场图片URL
    private int area;       //农场面积

    public Farm(){

    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getArea() {
        return area;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
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

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getLocation_name() {
        return location_name;
    }

    public int getFarm_id() {
        return farm_id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setFarm_id(int farm_id) {
        this.farm_id = farm_id;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
