package com.app.entity;

/**
 *
 * 视频基本bean
 */

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "video")
@Scope(value = "prototype")
public class Video {
    private int video_id;
    private String title;
    private String videopath;
    private int user_id_f;
    private int video_type;
    private String introduce;
    private String address;
    private int prize;
    private int look_persons;
    private String video_picture;
    private int interest_id_f;

    public Video(){}


    public int getInterest_id_f() {
        return interest_id_f;
    }

    public void setInterest_id_f(int interest_id_f) {
        this.interest_id_f = interest_id_f;
    }

    public String getVideo_picture() {
        return video_picture;
    }

    public void setVideo_picture(String video_picture) {
        this.video_picture = video_picture;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideopath() {
        return videopath;
    }

    public void setVideopath(String videopath) {
        this.videopath = videopath;
    }

    public int getUser_id_f() {
        return user_id_f;
    }

    public void setUser_id_f(int user_id_f) {
        this.user_id_f = user_id_f;
    }

    public int getVideo_type() {
        return video_type;
    }

    public void setVideo_type(int video_type) {
        this.video_type = video_type;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
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
}
