package com.app.service.impl;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-23
 */

import com.app.dao.IDynamicDao;
import com.app.entity.Dynamic;
import com.app.service.IDynamicService;
import com.app.vo.DynamicApiVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Service(value = "dynamicService")
public class DynamicServiceImpl implements IDynamicService {

    /*
    *@param:getDynamicById
    *@Description：通过dynamic_id 查找动态,
    *@Rerurn:DynamicApiVo
    */
    @Resource
    private DynamicApiVo dynamicApiVo;
    @Resource
    private IDynamicDao dynamicDao;

    public DynamicServiceImpl(){
    }

    public DynamicApiVo getDynamicById(int dynamic_id){
        Dynamic dynamic = dynamicDao.findDynamicById(dynamic_id);
        if(dynamic==null){
            dynamicApiVo.setCode(0);
            dynamicApiVo.setDynamic(null);
            dynamicApiVo.setDynamicList(null);
            dynamicApiVo.setMessage("未找到!");
        }else {
            dynamicApiVo.setCode(1);
            dynamicApiVo.setDynamic(dynamic);
            dynamicApiVo.setDynamicList(null);
            dynamicApiVo.setMessage("查找成功");
        }
        return dynamicApiVo;
    }

    public DynamicApiVo modifyDynamicById(Dynamic dynamic) {
        int dynamic_id = 0;
        try{
            dynamic_id = dynamicDao.findisDynamic(dynamic.getDynamic_id());
        }catch (Exception e){
            dynamic_id = 0;
        }
        if(dynamic_id!=0){
            dynamicDao.modifyDynamicById(dynamic);
            dynamicApiVo.setCode(1);
            dynamicApiVo.setDynamic(dynamic);
            dynamicApiVo.setDynamicList(null);
            dynamicApiVo.setMessage("修改成功");
        }else{
            dynamicApiVo.setCode(0);
            dynamicApiVo.setMessage("未找到此动态");
            dynamicApiVo.setDynamic(null);
            dynamicApiVo.setDynamicList(null);
        }
        return dynamicApiVo;
    }

    public DynamicApiVo deletePicById(Dynamic dynamic, String prepicfilepath) {
        try{
            String prepicpath = dynamicDao.findPicturePathByIDd(dynamic.getDynamic_id());
            String[] prepicpaths = prepicpath.split(";");
            String nowpiucpath = "";
            for(int i=0;i<prepicpaths.length;i++){
                System.out.println(prepicpaths[i]);
                if(prepicpaths[i].equals(prepicfilepath)){
                    prepicpaths[i] = dynamic.getPicture();
                }
                nowpiucpath = nowpiucpath+prepicpaths[i]+";";
            }
            System.out.println(nowpiucpath);
            dynamic.setPicture(nowpiucpath);
            dynamicDao.modifyPicById(dynamic);
            dynamicApiVo.setCode(1);
            dynamicApiVo.setDynamic(dynamic);
            dynamicApiVo.setDynamicList(null);
            dynamicApiVo.setMessage("修改成功");
        }catch (Exception e){
            dynamicApiVo.setCode(0);
            dynamicApiVo.setMessage("出现未知错误");
            dynamicApiVo.setDynamic(null);
            dynamicApiVo.setDynamicList(null);
        }
        return dynamicApiVo;
    }
    public DynamicApiVo findAllDynamicByUser_id(int user_id_f) {
        List<Dynamic> dynamics = dynamicDao.findAllDynamicByUserId(user_id_f);
        if(dynamics.size()==0){
            dynamicApiVo.setCode(0);
            dynamicApiVo.setDynamic(null);
            dynamicApiVo.setDynamicList(null);
            dynamicApiVo.setMessage("用户无动态");
        }else {
            dynamicApiVo.setCode(1);
            dynamicApiVo.setDynamic(null);
            dynamicApiVo.setDynamicList(dynamics);
            dynamicApiVo.setMessage("用户动态查找成功");
        }

        return dynamicApiVo;
    }
    public DynamicApiVo insertOneDynamic(Dynamic dynamic){
        try{
            dynamicDao.insertOneDynamic(dynamic);
            dynamicApiVo.setCode(1);
            dynamicApiVo.setDynamic(dynamic);
            dynamicApiVo.setDynamicList(null);
            dynamicApiVo.setMessage("添加成功");
        }catch (Exception e){
            dynamicApiVo.setCode(0);
            dynamicApiVo.setMessage("出现未知错误");
            dynamicApiVo.setDynamic(null);
            dynamicApiVo.setDynamicList(null);
        }
        return dynamicApiVo;
    }

    public DynamicApiVo deleteOneDynamicById(int dynamic_id) {
        int id = 0;
        try{
            id = dynamicDao.findisDynamic(dynamic_id);
        }catch (Exception e){
            id = 0;
        }
        if(id!=0){
            String picpaths = dynamicDao.findPicturePathByIDd(dynamic_id);
            String[] picpath = picpaths.split(";");
            for(String picstr:picpath){
                if(picpath!=null){
                    new File(picstr).delete();
                }
            }
            dynamicDao.deleteOneDynamicById(dynamic_id);
            dynamicApiVo.setCode(1);
            dynamicApiVo.setDynamic(null);
            dynamicApiVo.setDynamicList(null);
            dynamicApiVo.setMessage("删除成功");
        }else{
            dynamicApiVo.setCode(0);
            dynamicApiVo.setDynamic(null);
            dynamicApiVo.setDynamicList(null);
            dynamicApiVo.setMessage("未找到此动态");
        }
        return dynamicApiVo;
    }

    //暂不使用
    public DynamicApiVo insertPic(Dynamic dynamic) {
        int id = dynamicDao.findisDynamic(dynamic.getDynamic_id());
        if(id!=0){
            String prefilepath = dynamicDao.findPicturePathByIDd(dynamic.getDynamic_id());
            String nowfilepath = ";"+prefilepath+dynamic.getPicture();
            dynamic.setPicture(nowfilepath);
            dynamicDao.modifyPicById(dynamic);
            dynamicApiVo.setCode(1);
            dynamicApiVo.setDynamic(dynamic);
            dynamicApiVo.setDynamicList(null);
            dynamicApiVo.setMessage("修改成功");
        }else{
            dynamicApiVo.setCode(0);
            dynamicApiVo.setDynamic(null);
            dynamicApiVo.setDynamicList(null);
            dynamicApiVo.setMessage("无此用户");
        }

        return dynamicApiVo;
    }

    public DynamicApiVo findDynamicByType(boolean dynamic_type) {
        List<Dynamic> list = dynamicDao.findAllDynamicByType(dynamic_type);
        dynamicApiVo.setDynamicList(list);
        dynamicApiVo.setDynamic(null);
        if(dynamic_type){
            dynamicApiVo.setMessage("找到需求");
        }else {
            dynamicApiVo.setMessage("找到动态");
        }
        dynamicApiVo.setCode(1);

        return dynamicApiVo;
    }
}
