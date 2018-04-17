package com.app.entity;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
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
    private boolean video_type;

    public Video(){}

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

    public boolean isVideo_type() {
        return video_type;
    }

    public void setVideo_type(boolean video_type) {
        this.video_type = video_type;
    }
}
