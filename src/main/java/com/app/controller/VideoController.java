package com.app.controller;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
 */


import com.app.entity.Video;
import com.app.service.IVideoService;
import com.app.util.ApiFormatUtil;
import com.app.util.ChargePicUtil;
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
import java.sql.SQLException;

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
                                 @RequestParam("video_picture")CommonsMultipartFile video_picture,
                                 int user_id_f, int video_type, String introduce,
                                 @RequestParam(defaultValue = "成都") String address,
                                 @RequestParam(defaultValue = "0") int prize,
                                 @RequestParam(defaultValue = "0") int look_persons,HttpServletRequest request) throws SQLException{
        if(videofile.getOriginalFilename().equals("")){
            videoApiVo.setCode(0);
            videoApiVo.setVideoList(null);
            videoApiVo.setMsg("请放入视频");
            videoApiVo.setVideo(null);
        }else if(video_type>3||video_type<0){
            videoApiVo.setCode(0);
            videoApiVo.setVideoList(null);
            videoApiVo.setMsg("视频类型错误（0-3）");
            videoApiVo.setVideo(null);
        }else if(user_id_f<=0){
            videoApiVo.setCode(0);
            videoApiVo.setVideoList(null);
            videoApiVo.setMsg("用户id格式错误");
            videoApiVo.setVideo(null);
        }else if(!ChargeVidUtil.isVideo(videofile.getOriginalFilename())||!ChargePicUtil.ispic(video_picture.getOriginalFilename())){
            videoApiVo.setCode(0);
            videoApiVo.setVideoList(null);
            videoApiVo.setMsg("视频或者视频图片的格式错误");
            videoApiVo.setVideo(null);
        } else{
                String videopath = UploadUtil.upload(videofile,request,2);
                String videopicturepath = UploadUtil.upload(video_picture,request,3);
                Video video = new Video();
                video.setTitle(title);
                video.setUser_id_f(user_id_f);
                video.setVideo_type(video_type);
                video.setVideopath(videopath);
                video.setIntroduce(introduce);
                video.setAddress(address);
                video.setLook_persons(look_persons);
                video.setPrize(prize);
                video.setVideo_picture(videopicturepath);
                videoApiVo = videoService.insertOneVideo(video);
        }
        return ApiFormatUtil.apiFormat(videoApiVo.getCode(),videoApiVo.getMsg(),videoApiVo.getVideo());
    }

    @RequestMapping(value = "/findVideoById",method = {RequestMethod.GET, RequestMethod.POST}
            ,produces = "text/json;charset=utf-8")
    @ResponseBody
    public String findVideoById(int video_id){
        if(video_id<1){
            videoApiVo.setCode(0);
            videoApiVo.setVideoList(null);
            videoApiVo.setMsg("无此视频动态");
            videoApiVo.setVideo(null);
        }else{
            videoApiVo = videoService.findVideoById(video_id);
        }
        return ApiFormatUtil.apiFormat(videoApiVo.getCode(),videoApiVo.getMsg(),videoApiVo.getVideo());
    }


    @RequestMapping(value = "/findVideosByType",method = {RequestMethod.GET, RequestMethod.POST}
            ,produces = "text/json;charset=utf-8")
    @ResponseBody
    public String findVideosByType(int video_type){
        if(video_type<0){
            videoApiVo.setCode(0);
            videoApiVo.setVideoList(null);
            videoApiVo.setMsg("请输入正确的视频类型");
            videoApiVo.setVideo(null);
        }else {
            videoApiVo = videoService.findVideosByType(video_type);
        }
        return ApiFormatUtil.apiFormat(videoApiVo.getCode(),videoApiVo.getMsg(),videoApiVo.getVideoList());
    }


    @RequestMapping(value = "/findVideosByUser_id_f",method = {RequestMethod.GET, RequestMethod.POST}
            ,produces = "text/json;charset=utf-8")
    @ResponseBody
    public String findVideosByUser_id_f(int user_id_f){
        if(user_id_f<0){
            videoApiVo.setCode(0);
            videoApiVo.setVideoList(null);
            videoApiVo.setMsg("请输入正确用户id");
            videoApiVo.setVideo(null);
        }else{
            videoApiVo = videoService.findVideosByUser_id_f(user_id_f);
        }
        return ApiFormatUtil.apiFormat(videoApiVo.getCode(),videoApiVo.getMsg(),videoApiVo.getVideoList());
    }


    @RequestMapping(value = "/modifyVideoById",method = {RequestMethod.GET, RequestMethod.POST}
            ,produces = "text/json;charset=utf-8")
    @ResponseBody
    public String modifyVideoById(int video_id,String title,String introduce,String address,
                                  int prize,int look_persons){
        if(video_id<0){
            videoApiVo.setCode(0);
            videoApiVo.setVideoList(null);
            videoApiVo.setMsg("请输入正确用户id");
            videoApiVo.setVideo(null);
        }else {
            Video video = new Video();
            video.setVideo_id(video_id);
            video.setIntroduce(introduce);
            video.setTitle(title);
            video.setAddress(address);
            video.setLook_persons(look_persons);
            video.setPrize(prize);
            videoApiVo = videoService.modifyVideoById(video);
        }
        return ApiFormatUtil.apiFormat(videoApiVo.getCode(),videoApiVo.getMsg(),videoApiVo.getVideo());
    }


    @RequestMapping(value = "/modifyVideo_videoById",method = {RequestMethod.POST}
            ,produces = "text/json;charset=utf-8")
    @ResponseBody
    public String modifyVideo_videoById(int video_id, @RequestParam("videofile") CommonsMultipartFile videofile,
                                        @RequestParam("video_picture") CommonsMultipartFile video_picture,HttpServletRequest request){
            if(!videofile.getOriginalFilename().equals("")||!video_picture.getOriginalFilename().equals("")){
                if(!ChargeVidUtil.isVideo(videofile.getOriginalFilename())
                        ||!ChargePicUtil.ispic(video_picture.getOriginalFilename())) {
                    videoApiVo.setCode(0);
                    videoApiVo.setVideoList(null);
                    videoApiVo.setMsg("视频或者视频图片格式错误");
                    videoApiVo.setVideo(null);
                }else{
                    String videopath = UploadUtil.upload(videofile,request,2);
                    String videopicturepath = UploadUtil.upload(video_picture,request,3);
                    Video video = new Video();
                    video.setVideopath(videopath);
                    video.setVideo_picture(videopicturepath);
                    video.setVideo_id(video_id);
                    videoApiVo = videoService.modifyVideo_videoById(video);
                }
            }else{
                Video video = new Video();
                video.setVideopath("");
                video.setVideo_picture("");
                video.setVideo_id(video_id);
                videoApiVo = videoService.modifyVideo_videoById(video);
            }
        return ApiFormatUtil.apiFormat(videoApiVo.getCode(),videoApiVo.getMsg(),videoApiVo.getVideo());
    }


    @RequestMapping(value = "/deleteVideoById",method = {RequestMethod.GET, RequestMethod.POST}
            ,produces = "text/json;charset=utf-8")
    @ResponseBody
    public String deleteVideoById(int video_id){
        if(video_id<=0){
            videoApiVo.setCode(0);
            videoApiVo.setVideoList(null);
            videoApiVo.setMsg("视频的id格式错误");
            videoApiVo.setVideo(null);
        }else{
            videoApiVo = videoService.deleteVideoById(video_id);
        }
        return ApiFormatUtil.apiFormat(videoApiVo.getCode(),videoApiVo.getMsg(),videoApiVo.getVideoList());
    }
}
