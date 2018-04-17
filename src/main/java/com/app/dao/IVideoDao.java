package com.app.dao;/*
 *@Author:dxlin
 *@Description：用于视频的Dao层
 *@Date: 2018-4-14
 */

import com.app.entity.Video;

public interface IVideoDao {

    public void insertOneVideo(Video video);

    public Video findVideoById(int video_id);
}
