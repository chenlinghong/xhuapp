package com.app.service.impl;

import com.app.dao.IDynamicDao;
import com.app.entity.Dynamic;
import com.app.service.IDynamicService;
import com.app.vo.DynamicApiVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-23
 */

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
        int dynamic_id = dynamicDao.findisDynamic(dynamic.getDynamic_id());
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

    public DynamicApiVo modifyPicById(Dynamic dynamic) {
        try{
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


}
