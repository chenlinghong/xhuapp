package com.app.controller;

import com.app.dto.FarmDto;
import com.app.entity.Farm;
import com.app.service.IFarmService;
import com.app.service.IUserService;
import com.app.util.ApiFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
