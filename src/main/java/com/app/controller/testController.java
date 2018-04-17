package com.app.controller;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
 */


import com.app.dao.IDynamicDao;
import com.app.entity.Dynamic;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping(value = "/test")
public class testController {
    @Resource
    IDynamicDao dynamicDao;
    @Resource
    Dynamic dynamic;


    @RequestMapping(value = "/hello",method = {RequestMethod.GET,RequestMethod.POST},
                        produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String testHello(@RequestParam("picfile") CommonsMultipartFile file, HttpServletRequest request){
        String str = "";
        if(file.getOriginalFilename().equals("")){
            str = "no";
        }
        System.out.println(file.getOriginalFilename()+"ok");
        return str;
    }

    public static void main(String args[]){
        new File("D:\\软件\\tomacat\\apache-tomcat-9.0.0.M21\\webapps\\ROOT\\upload\\imgs\\63411723_p0.png").delete();
    }

}
