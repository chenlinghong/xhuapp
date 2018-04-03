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
import javax.servlet.http.HttpServletRequest;
import java.io.*;
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

    public DynamicApiVo modifyDynamicById(Dynamic dynamic,HttpServletRequest request){
        String upfillepaths = dynamic.getPicture();
        if(dynamicDao.findisDynamic(dynamic.getDynamic_id())==null){
            dynamicApiVo.setCode(0);
            dynamicApiVo.setDynamic(null);
            dynamicApiVo.setDynamicList(null);
            dynamicApiVo.setMessage("无此动态!");
            return dynamicApiVo;
        }
        String prefilepaths = dynamicDao.findPicturePathByIDd(dynamic.getDynamic_id());
        String[] upfilepatharr = upfillepaths.split(";");
        String[] prefilepatharr = prefilepaths.split(";");
        int i = 0;
        while(i<upfilepatharr.length){
            //现在上传的图片的地址
            String upfillepath = upfilepatharr[i];
            //已经存在数据库的图片的地址
            String prefilepath = prefilepatharr[i];
            File upfile = new File(upfillepath);
            File prefile = new File(prefilepath);
            String imgtype = upfile.getName().split("\\.")[1];
            if(imgtype.equals("jpg")||imgtype.equals("png")||imgtype.equals("gif")&&!(dynamicDao.findisDynamic(dynamic.getDynamic_id())==null)){
                //即将存在数据库的图片的地址
                String path = request.getSession().getServletContext().getRealPath("/");
                String modifypath = path+"upload/imgs/"+prefile.getName();
                File modifyfile = new File(modifypath);
                try {
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(upfile));
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(modifyfile));
                    int flush = 0;
                    byte[] bytes = new byte[1024];
                    while((flush=bis.read(bytes))!=-1){
                        bos.write(bytes,0,flush);
                    }
                    i++;
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }else{
                dynamicApiVo.setCode(0);
                dynamicApiVo.setDynamic(null);
                dynamicApiVo.setDynamicList(null);
                dynamicApiVo.setMessage("图片格式错误");
                return dynamicApiVo;
            }
        }
        dynamic.setPicture(prefilepaths);
        dynamicDao.modifyDynamicById(dynamic);
        dynamicApiVo.setCode(1);
        dynamicApiVo.setDynamicList(null);
        dynamicApiVo.setDynamic(dynamic);
        dynamicApiVo.setMessage("修改成功");
        return dynamicApiVo;
    }
//    public static void main(String args[]){
//        String pictures = "D:\\mypic\\img.jpg";
//        String[] uppathnames = pictures.split(";");
//        String downfilepaths = "";
//        int i = 0;
//        while(i<uppathnames.length){
//            System.out.println(System.getProperty( "user.dir" ));
//            String pathname = uppathnames[i];
//            File upfile = new File(pathname);
//            String jpg = upfile.getName();
//            String imgtype = jpg.split("\\.")[1];
//            //判断是否为图片文件
//            if(imgtype.equals("jpg")||imgtype.equals("png")||imgtype.equals("gif")){
//                File downfile = new File("upload/imgs"+upfile.getName());
//                BufferedInputStream bis = null;
//                try {
//                    bis = new BufferedInputStream(new FileInputStream(upfile));
//                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(downfile));
//                    int flush = 0;
//                    byte[] bytes = new byte[1024];
//                    while((flush=bis.read(bytes))!=-1){
//                        bos.write(bytes,0,flush);
//                    }
//                    downfilepaths = downfilepaths+downfile.getAbsolutePath()+";";
//                    System.out.println(downfilepaths);
//                    i++;
//                    continue;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    break;
//                }
//            }else{
//
//            }
//        }
//
//    }

    public DynamicApiVo insertOneDynamic(Dynamic dynamic, HttpServletRequest request){
        String pictures = dynamic.getPicture();
        String[] uppathnames = pictures.split(";");
        String downfilepaths = "";
        int i = 0;
        while(i<uppathnames.length){
            String pathname = uppathnames[i];
            File upfile = new File(pathname);
            String jpg = upfile.getName();
            String imgtype = jpg.split("\\.")[1];
            //判断是否为图片文件
            if(imgtype.equals("jpg")||imgtype.equals("png")||imgtype.equals("gif")){
                String path = request.getSession().getServletContext().getRealPath("/");
                File downfile = new File(path+"upload/imgs/"+upfile.getName());
                BufferedInputStream bis = null;
                try {
                    bis = new BufferedInputStream(new FileInputStream(upfile));
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(downfile));
                    int flush = 0;
                    byte[] bytes = new byte[1024];
                    while((flush=bis.read(bytes))!=-1){
                        bos.write(bytes,0,flush);
                    }
                    downfilepaths = downfilepaths+downfile.getAbsolutePath()+";";
                    i++;
                    continue;
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }else{
                dynamicApiVo.setCode(0);
                dynamicApiVo.setDynamic(null);
                dynamicApiVo.setDynamicList(null);
                dynamicApiVo.setMessage("图片格式错误");
                return dynamicApiVo;
            }
        }
        dynamic.setPicture(downfilepaths);
        dynamicDao.insertOneDynamic(dynamic);
        dynamicApiVo.setCode(1);
        dynamicApiVo.setDynamic(dynamic);
        dynamicApiVo.setDynamicList(null);
        dynamicApiVo.setMessage("添加成功");
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
