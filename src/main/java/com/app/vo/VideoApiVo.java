package com.app.vo;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
 */

import com.app.entity.Video;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "videoApiVo")
@Scope(value = "prototype")
public class VideoApiVo {
    private int code;
    private String msg;
    private List<Video> videoList;
    private Video video;

    public VideoApiVo(){}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
