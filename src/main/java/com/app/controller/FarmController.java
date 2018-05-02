package com.app.controller;

import com.app.dto.FarmAddDto;
import com.app.dto.FarmDto;
import com.app.entity.Farm;
import com.app.service.IFarmService;
import com.app.service.IUserService;
import com.app.util.ApiFormatUtil;
import com.app.util.UploadHead_portrailUtil;
import com.app.vo.UserApiVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: xhuapp
 * @description: 农场相关API类
 * @author: Mr.Chen
 * @create: 2018-04-24 08:31
 **/
@Controller
@RequestMapping(value = "/user/farm")
public class FarmController {

    @Autowired
    private IFarmService farmService;

    @Autowired
    private IUserService userService;


    /**
     * 根据用户ID获得农场信息
     * @param user_id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get",method = {RequestMethod.POST,RequestMethod.GET},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getFarmByUser_id(int user_id) throws Exception{
//        String resultJson = "";
        FarmDto farmDto = farmService.findFarmByUser_id(user_id);
        if (farmDto.getCode() == 0){
            farmDto.setFarm(null);
        } else {
            Farm farm = farmDto.getFarm();
            if (farm == null){
                farmDto.setCode(0);
                farmDto.setMessage("获取农场信息失败！");
//                resultJson = "";
            } else {
                //TODO
            }
        }
        return ApiFormatUtil.apiFormat(farmDto.getCode(),farmDto.getMessage(),farmDto.getFarm());
    }


    /**
     * 新增农场
     * @param farmAddDto
     *      name : 农场名称
     *      longitude : 经度
     *      latitude : 纬度
     *      location_name : 位置名称
     *      user_id : 用户ID
     *      introduce ： 农场介绍
     *      area ： 农场面积
     * @param files 农场图片（多张）
     * @param request
     * @return
     */
    @RequestMapping(value = "/add",method = {RequestMethod.POST,RequestMethod.GET},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String addFarm(FarmAddDto farmAddDto,@RequestParam("files")MultipartFile[] files, HttpServletRequest request){
        FarmDto farmDto = new FarmDto();
        farmDto.setCode(1);
        farmDto.setMessage("");
        farmDto.setFarm(null);

        String photo = "";

        //判断file数组不能为空并且长度大于0
        if(files!=null && files.length>0){
            //循环获取file数组中得文件
            for(int i = 0;i<files.length;i++){
                MultipartFile file = files[i];
                //保存文件
                String temp = UploadHead_portrailUtil.uploadHead_portrail(file,request);
                photo += " " + temp;
            }
        }

        if (farmAddDto == null){
            farmDto.setCode(0);
            farmDto.setMessage(farmDto.getMessage() + "传参错误！");
        } else{
            //判断用户是否存在
            try {
                UserApiVo userApiVo = userService.getUserByUser_id(farmAddDto.getUser_id());
                if (userApiVo.getCode() == 0){
                    farmDto.setCode(0);
                    farmDto.setMessage(farmDto.getMessage() + "获取用户信息失败！");
                } else {
                    Farm farm = new Farm();
                    farm.setUser_id(farmAddDto.getUser_id());
                    String longitude = farmAddDto.getLongitude();
                    String latitude = farmAddDto.getLatitude();
                    String name = farmAddDto.getName();
                    String location_name = farmAddDto.getLocation_name();
                    String introduce = farmAddDto.getIntroduce();
                    int area = farmAddDto.getArea();

//                    System.out.println("---------------------------------------------");
//                    System.out.println("longitude:" + longitude);
//                    System.out.println("latitude: " + latitude);
//                    System.out.println("user_id:" + farmAddDto.getUser_id());
//                    System.out.println("---------------------------------------------");

                    if (longitude == "" || longitude == null){
                        farmDto.setCode(0);
                        farmDto.setMessage(farmDto.getMessage() + "经度为空！");
                    } else {
                        longitude = longitude.trim();
                        farm.setLongitude(longitude);
                    }
                    if (latitude == "" || latitude == null){
                        farmDto.setCode(0);
                        farmDto.setMessage(farmDto.getMessage() + "纬度为空！");
//                        System.out.println("latitude: ------ " + latitude);
                    } else {
                        latitude = latitude.trim();
                        farm.setLatitude(latitude);
                    }
                    if (name != null && name != ""){
                        farm.setName(name);
                    }
                    if (location_name != null && location_name != ""){
                        farm.setLocation_name(location_name);
                    }
                    if (introduce != null && introduce != ""){
                        farm.setIntroduce(introduce);
                    }
                    if (area > 0){
                        farm.setArea(area);
                    } else {
                        farm.setArea(0);
                    }
                    if (farmDto.getCode() == 1) {
                        FarmDto addResult = farmService.insertFarm(farm);
                        farmDto.setCode(addResult.getCode());
                        farmDto.setMessage(addResult.getMessage());
                        farmDto.setFarm(addResult.getFarm());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                farmDto.setCode(0);
                farmDto.setMessage(farmDto.getMessage() + "服务器内部错误！");
            }

        }
        return ApiFormatUtil.apiFormat(farmDto.getCode(),farmDto.getMessage(),"");
    }


    /**
     * 修改农场基本信息
     * @param farmAddDto
     *      name : 农场名称
     *      longitude : 经度
     *      latitude : 纬度
     *      location_name : 位置名称
     *      user_id : 用户ID
     *      introduce ： 农场介绍
     *      area ： 农场面积
     * @return
     */
    @RequestMapping(value = "/modify",method = {RequestMethod.POST,RequestMethod.GET},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String modifyFarmInfo(FarmAddDto farmAddDto){
        FarmDto farmDto = new FarmDto();
        farmDto.setCode(1);
        farmDto.setMessage("");
        farmDto.setFarm(null);

        if (farmAddDto == null){
            farmDto.setCode(0);
            farmDto.setMessage(farmDto.getMessage() + "传参错误！");
        } else{
            Farm farm = new Farm();
            farm.setUser_id(farmAddDto.getUser_id());

            String longitude = farmAddDto.getLongitude();
            String latitude = farmAddDto.getLatitude();
            String name = farmAddDto.getName();
            String location_name = farmAddDto.getLocation_name();
            String introduce = farmAddDto.getIntroduce();
            int area = farmAddDto.getArea();

            if (longitude != "" && longitude != null){
                longitude = longitude.trim();
                farm.setLongitude(longitude);
            }
            if (latitude != "" && latitude != null){
                latitude = longitude.trim();
                farm.setLongitude(latitude);
            }
            if (name != null && name != ""){
                farm.setName(name);
            }
            if (location_name != null && location_name != ""){
                farm.setLongitude(location_name);
            }
            if (introduce != null && introduce != ""){
                farm.setIntroduce(introduce);
            }
            if (area > 0){
                farm.setArea(area);
            } else {
                farm.setArea(0);
            }

            try {
                FarmDto modifyResult = farmService.modifyFarmInfo(farm);
                farmDto.setCode(modifyResult.getCode());
                farmDto.setMessage(modifyResult.getMessage());

            } catch (Exception e) {
                e.printStackTrace();
                farmDto.setCode(0);
                farmDto.setMessage("服务器内部错误！");
            }
        }
        return ApiFormatUtil.apiFormat(farmDto.getCode(),farmDto.getMessage(),"");
    }


    /**
     * 修改农场图片
     * @param files
     * @param user_id
     * @return
     */
    @RequestMapping(value = "/modifyphoto",method = {RequestMethod.POST,RequestMethod.GET},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String modifyPhoto(@RequestParam("files")MultipartFile[] files,int user_id,HttpServletRequest request) {
        FarmDto farmDto = new FarmDto();
        farmDto.setCode(1);
        farmDto.setMessage("");
        farmDto.setFarm(null);

        String photo = "";

        //判断file数组不能为空并且长度大于0
        if (files != null && files.length > 0) {
            //循环获取file数组中得文件
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                //保存文件
                String temp = UploadHead_portrailUtil.uploadHead_portrail(file, request);
                photo += " " + temp;
            }
        } else {
            //默认用户欲删除所有图片
        }

        try {
            UserApiVo userApiVo = userService.getUserByUser_id(user_id);
            if (userApiVo.getCode() == 0 || userApiVo.getUser() == null) {
                farmDto.setCode(0);
                farmDto.setMessage(farmDto.getMessage() + "用户不存在！");
            } else {
                FarmDto modifyPhoto = farmService.modifyPhoto(photo, user_id);
                farmDto.setCode(modifyPhoto.getCode());
                farmDto.setMessage(modifyPhoto.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            farmDto.setCode(0);
            farmDto.setMessage("服务器内部错误");
        }
        return ApiFormatUtil.apiFormat(farmDto.getCode(),farmDto.getMessage(),"");
    }

}
