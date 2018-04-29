package com.app.dao;/*
 *@Author:dxlin
 *@Description：用于视频的Dao层
 *@Date: 2018-4-14
 */

import com.app.entity.Video;

import java.util.List;

public interface IVideoDao {

    public void insertOneVideo(Video video);

    public Video findVideoById(int video_id);

    public List<Video> findVideosByType(int video_type);

    public List<Video> findVideosByUser_id_f(int user_id_f);

    public List<String> findVideoPathById(int video_id);

    public void modifyVideoById(Video video);

    public void modifyVideo_videoById(Video video);

    public void deleteVideoById(int video_id);


}
