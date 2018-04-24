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
import java.io.File;
import java.util.List;

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
            e.printStackTrace();
        }
        return videoApiVo;
    }

    public VideoApiVo findVideoById(int video_id) {
        try{
            Video video = videoDao.findVideoById(video_id);
            videoApiVo.setCode(1);
            videoApiVo.setMsg("成功查询");
            videoApiVo.setVideo(video);
        }catch (Exception e){
            videoApiVo.setCode(0);
            videoApiVo.setMsg("查询失败");
            videoApiVo.setVideo(null);
        }

        return videoApiVo;
    }

    public VideoApiVo findVideosByType(int video_type) {
        if(video_type<4&&video_type>=0){
            List<Video> videoList = videoDao.findVideosByType(video_type);
            videoApiVo.setCode(1);
            videoApiVo.setMsg("成功查询");
            videoApiVo.setVideoList(videoList);
        }else{
            videoApiVo.setCode(0);
            videoApiVo.setMsg("查询失败,请输入正确的类型（0-3）");
            videoApiVo.setVideoList(null);
        }
        return videoApiVo;
    }

    public VideoApiVo findVideosByUser_id_f(int user_id_f) {
        List<Video> videoList = videoDao.findVideosByUser_id_f(user_id_f);
        if(videoList.isEmpty()){
            videoApiVo.setCode(0);
            videoApiVo.setMsg("用户无视频");
            videoApiVo.setVideoList(null);
        }else{
            videoApiVo.setCode(1);
            videoApiVo.setMsg("查询成功");
            videoApiVo.setVideoList(videoList);
        }
        return videoApiVo;
    }

    public VideoApiVo modifyVideoById(Video video) {
        try{
            videoDao.modifyVideoById(video);
            videoApiVo.setCode(1);
            videoApiVo.setVideo(video);
            videoApiVo.setMsg("修改成功");
        }catch (Exception e){
            videoApiVo.setCode(0);
            videoApiVo.setVideo(video);
            videoApiVo.setMsg("修改失败");
        }
        return videoApiVo;
    }

    public VideoApiVo modifyVideo_videoById(Video video) {
        String prevideopath = videoDao.findVideoPathById(video.getVideo_id());
        try{
            if(!prevideopath.equals("")){
                new File(prevideopath).delete();
            }
            videoDao.modifyVideo_videoById(video);
            videoApiVo.setVideo(video);
            videoApiVo.setMsg("修改成功");
            videoApiVo.setCode(1);
        }catch (Exception e){
//            videoApiVo.setVideo(null);
//            videoApiVo.setMsg("修改失败");
//            videoApiVo.setCode(0);
            e.printStackTrace();
        }
        return videoApiVo;
    }

    public VideoApiVo deleteVideoById(int video_id) {
        String prevideopath = videoDao.findVideoPathById(video_id);
        try{
            new File(prevideopath).delete();
            videoDao.deleteVideoById(video_id);
            videoApiVo.setVideo(null);
            videoApiVo.setMsg("删除成功");
            videoApiVo.setCode(1);
        }catch (Exception e){
            videoApiVo.setVideo(null);
            videoApiVo.setMsg("删除失败");
            videoApiVo.setCode(0);
        }
        return videoApiVo;
    }
}
