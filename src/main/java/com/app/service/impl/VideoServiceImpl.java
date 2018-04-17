package com.app.service.impl;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
 */

import com.app.dao.IVideoDao;
import com.app.entity.Video;
import com.app.service.IVideoService;
import com.app.vo.VideoApiVo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component(value = "videoService")
@Scope(value = "prototype")
public class VideoServiceImpl implements IVideoService{

    @Resource
    private VideoApiVo videoApiVo;
    @Resource
    private IVideoDao videoDao;


    public VideoApiVo insertOneVideo(Video video) {
        try{
            videoDao.insertOneVideo(video);
            videoApiVo.setCode(1);
            videoApiVo.setVideo(video);
            videoApiVo.setVideoList(null);
            videoApiVo.setMsg("添加成功");
        }catch (Exception e){
            videoApiVo.setCode(0);
            videoApiVo.setVideo(null);
            videoApiVo.setVideoList(null);
            videoApiVo.setMsg("添加失败");
        }
        return videoApiVo;
    }
}
