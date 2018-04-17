package com.app.controller;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
 */


import com.app.entity.Video;
import com.app.service.IVideoService;
import com.app.util.ApiFormatUtil;
import com.app.util.ChargeVidUtil;
import com.app.util.UploadUtil;
import com.app.vo.VideoApiVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Video")
public class VideoController {
    @Resource
    private VideoApiVo videoApiVo;
    @Resource
    private IVideoService videoService;

    @RequestMapping(value = "/insertOneVideo",method = {RequestMethod.GET, RequestMethod.POST}
                        ,produces = "text/json;charset=utf-8")
    @ResponseBody
    public String insertOneVideo(String title, @RequestParam("videofile")CommonsMultipartFile videofile,
                                 int user_id_f, boolean video_type, HttpServletRequest request){
        if(user_id_f<=0){
            videoApiVo.setCode(0);
            videoApiVo.setVideoList(null);
            videoApiVo.setMsg("无此用户");
            videoApiVo.setVideo(null);
        }else if(!ChargeVidUtil.isVideo(videofile.getOriginalFilename())){
            videoApiVo.setCode(0);
            videoApiVo.setVideoList(null);
            videoApiVo.setMsg("视频格式错误");
            videoApiVo.setVideo(null);
        }else{
            try{
                String videopath = UploadUtil.upload(videofile,request,false);
                Video video = new Video();
                video.setTitle(title);
                video.setUser_id_f(user_id_f);
                video.setVideo_type(video_type);
                video.setVideopath(videopath);
                videoApiVo = videoService.insertOneVideo(video);
            }catch (Exception e){
                videoApiVo.setCode(0);
                videoApiVo.setVideoList(null);
                videoApiVo.setMsg("出现未知错误");
                videoApiVo.setVideo(null);
            }
        }
        return ApiFormatUtil.apiFormat(videoApiVo.getCode(),videoApiVo.getMsg(),videoApiVo.getVideo());
    }
}
