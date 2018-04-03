package com.app.controller;

import com.app.entity.Dynamic;
import com.app.service.IDynamicService;
import com.app.util.ApiFormatUtil;
import com.app.vo.DynamicApiVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/*
 *@Author:dxlin
 *@Description：用于动态的相关api
 *@Date: 2018-3-23
 */
@Controller
@RequestMapping(value = "/Dynamic")
public class DynamicController {
    @Resource
    IDynamicService dynamicService;

    public DynamicController(){}


    @RequestMapping(value = "/findDynamicById",method = {RequestMethod.GET,RequestMethod.POST},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String findDynamicById(int dynamic_id){
        Dynamic result = new Dynamic();
        DynamicApiVo dynamicApiVo = new DynamicApiVo();
        if(dynamic_id<=0){
            dynamicApiVo.setCode(0);
            dynamicApiVo.setMessage("数据不合法");
            dynamicApiVo.setDynamic(null);
        }else{
            dynamicApiVo = dynamicService.getDynamicById(dynamic_id);
        }
        return ApiFormatUtil.apiFormat(dynamicApiVo.getCode(),dynamicApiVo.getMessage(),dynamicApiVo.getDynamic());
    }


    @RequestMapping(value = "/modifyDynamicById",method = {RequestMethod.GET,RequestMethod.POST},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String modifyDynamicById(int dynamic_id,String title,String introduce,String picture,int user_id_f,HttpServletRequest request){
        Dynamic result = new Dynamic();
        DynamicApiVo dynamicApiVo = new DynamicApiVo();
        if(dynamic_id<=0){
            dynamicApiVo.setCode(0);
            dynamicApiVo.setMessage("数据不合法");
            dynamicApiVo.setDynamic(null);
        }else{
            result.setDynamic_id(dynamic_id);
            result.setIntroduce(introduce);
            result.setTitle(title);
            result.setPicture(picture);
            result.setUser_id_f(user_id_f);
            dynamicApiVo = dynamicService.modifyDynamicById(result,request);
        }
        return ApiFormatUtil.apiFormat(dynamicApiVo.getCode(),dynamicApiVo.getMessage(),dynamicApiVo.getDynamic());
    }

    @RequestMapping(value = "/insertOneDynamic",method = {RequestMethod.GET,RequestMethod.POST},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String insertOneDynamic(String title, String introduce, String picture, int user_id_f, HttpServletRequest request){
        Dynamic newdynamic = new Dynamic();
        DynamicApiVo dynamicApiVo = new DynamicApiVo();
        if(user_id_f<=0){
            dynamicApiVo.setCode(0);
            dynamicApiVo.setMessage("不存在这个用户");
            dynamicApiVo.setDynamic(null);
        }else{
            newdynamic.setIntroduce(introduce);
            newdynamic.setTitle(title);
            newdynamic.setPicture(picture);
            newdynamic.setUser_id_f(user_id_f);
            dynamicApiVo = dynamicService.insertOneDynamic(newdynamic,request);
        }
        return ApiFormatUtil.apiFormat(dynamicApiVo.getCode(),dynamicApiVo.getMessage(),dynamicApiVo.getDynamic());
    }


    @RequestMapping(value = "/findAllDynamicByUserId",method = {RequestMethod.GET,RequestMethod.POST},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String findAllDynamicByUserId(int user_id_f){
        DynamicApiVo dynamicApiVo = new DynamicApiVo();
        if(user_id_f<=0){
            dynamicApiVo.setCode(0);
            dynamicApiVo.setDynamicList(null);
            dynamicApiVo.setMessage("不存在这个用户");
            dynamicApiVo.setDynamic(null);
        }else{
            dynamicApiVo = dynamicService.findAllDynamicByUser_id(user_id_f);
        }
        return ApiFormatUtil.apiFormat(dynamicApiVo.getCode(),dynamicApiVo.getMessage(),dynamicApiVo.getDynamicList());
    }
//    public static void main(String args[]){
//        IDynamicService dynamicService = new DynamicServiceImpl();
//        Dynamic result = new Dynamic();
//        DynamicApiVo dynamicApiVo = new DynamicApiVo();
//        dynamicApiVo = dynamicService.getDynamicById(2);
//        System.out.println(ApiFormatUtil.apiFormat(dynamicApiVo.getCode(),dynamicApiVo.getMessage(),dynamicApiVo.getDynamic()));
//    }
}
