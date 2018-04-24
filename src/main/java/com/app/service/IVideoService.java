package com.app.service;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
 */

import com.app.entity.Video;
import com.app.vo.VideoApiVo;

public interface IVideoService {
    public VideoApiVo insertOneVideo(Video video);

    public VideoApiVo findVideoById(int video_id);

    public VideoApiVo findVideosByType(int video_type);

    public VideoApiVo findVideosByUser_id_f(int user_id_f);

    public VideoApiVo modifyVideoById(Video video);

    public VideoApiVo modifyVideo_videoById(Video video);

    public VideoApiVo deleteVideoById(int video_id);

}
