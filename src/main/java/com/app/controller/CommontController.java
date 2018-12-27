package com.app.controller;
/**
 * 评论相关Controller
 */


import com.app.entity.Commont;
import com.app.service.ICommontService;
import com.app.util.ApiFormatUtil;
import com.app.vo.CommontApiVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/Commont")
public class CommontController {


    @Resource
    private ICommontService commontService;
    @Resource
    private CommontApiVo commontApiVo;

    @RequestMapping(value = "/insertOneCommont",method = {RequestMethod.POST,RequestMethod.GET}
            ,produces = "text/json;charset=utf-8")
    @ResponseBody
    public String insertOneCommont(int user_id_f,String commont_body,
                                   @RequestParam(defaultValue = "-1") int father_commont_id) throws Exception{
        if(user_id_f<=0){
            commontApiVo.setMessage("用户id不合法(>0)");
            commontApiVo.setCode(0);
        }else{
            Commont commont = new Commont();
            commont.setCommont_body(commont_body);
            commont.setFather_commont_id(father_commont_id);
            commont.setUser_id_f(user_id_f);
            commontApiVo = commontService.insertOneCommont(commont);
        }
        return ApiFormatUtil.apiFormat(commontApiVo.getCode(),commontApiVo.getMessage(),null);
    }

    @RequestMapping(value = "/findCommontById",method = {RequestMethod.POST,RequestMethod.GET}
            ,produces = "text/json;charset=utf-8")
    @ResponseBody
    public String findCommontById(int commont_id, HttpServletResponse response) throws Exception{

        if(commont_id<=0){
            commontApiVo.setMessage("评论id不合法(>0)");
            commontApiVo.setCode(0);
            commontApiVo.setCommont(null);
        }else{
            commontApiVo = commontService.findCommontById(commont_id);
        }
        return ApiFormatUtil.apiFormat(commontApiVo.getCode(),commontApiVo.getMessage(),commontApiVo.getCommont())+response.getContentType();
    }


    @RequestMapping(value = "/findCommontsByUser_id",method = {RequestMethod.POST,RequestMethod.GET}
            ,produces = "text/json;charset=utf-8")
    @ResponseBody
    public String findCommontsByUser_id(int user_id_f) throws Exception{
        if(user_id_f<=0){
            commontApiVo.setMessage("用户id不合法(>0)");
            commontApiVo.setCode(0);
            commontApiVo.setCommontList(null);
        }else{
            commontApiVo = commontService.findCommontsByUser_id(user_id_f);
        }
        return ApiFormatUtil.apiFormat(commontApiVo.getCode(),commontApiVo.getMessage(),commontApiVo.getCommontList());

    }

    @RequestMapping(value = "/findFather_CommontByCommont_id",method = {RequestMethod.POST,RequestMethod.GET}
            ,produces = "text/json;charset=utf-8")
    @ResponseBody
    public String findFather_CommontByCommont_id(int commont_id) throws Exception{
        if(commont_id<=0){
            commontApiVo.setCommont(null);
            commontApiVo.setCode(0);
            commontApiVo.setMessage("评论id不合法(>0)");
        }else{
            commontApiVo = commontService.findFather_CommontByCommont_id(commont_id);
        }
        return ApiFormatUtil.apiFormat(commontApiVo.getCode(),commontApiVo.getMessage(),commontApiVo.getCommont());

    }

    @RequestMapping(value = "/findSon_Commont_idByCommont_id",method = {RequestMethod.POST,RequestMethod.GET}
            ,produces = "text/json;charset=utf-8")
    @ResponseBody
    public String findSon_Commont_idByCommont_id(int commont_id) throws Exception{
        if(commont_id<=0){
            commontApiVo.setCode(0);
            commontApiVo.setMessage("评论id不合法(>0)");
            commontApiVo.setCommont(null);
        }else{
            commontApiVo = commontService.findSon_Commont_idByCommont_id(commont_id);
        }
        return ApiFormatUtil.apiFormat(commontApiVo.getCode(),commontApiVo.getMessage(),commontApiVo.getCommontList());
    }


    @RequestMapping(value = "/deleteCommontById",method = {RequestMethod.POST,RequestMethod.GET}
            ,produces = "text/json;charset=utf-8")
    @ResponseBody
    public String deleteCommontById(int commont_id) throws Exception{
        if(commont_id<=0){
            commontApiVo.setMessage("评论id不合法(>0)");
            commontApiVo.setCommont(null);
            commontApiVo.setCode(0);
        }else{
            commontService.deleteCommontById(commont_id);
        }
        return ApiFormatUtil.apiFormat(commontApiVo.getCode(),commontApiVo.getMessage(),null);
    }

}
