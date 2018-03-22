package com.app.controller;

import com.app.entity.User;
import com.app.service.IUserService;
import com.app.util.ApiFormatUtil;
import com.app.vo.UserApiVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @program: busis
 * @description: User相关操作API类，
 * @author: Mr.Chen
 * @create: 2018-03-13 14:42
 **/

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private IUserService userService;


    //无参构造
    public UserController(){

    }

    @RequestMapping(value = "/test", method = {RequestMethod.POST,RequestMethod.GET},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String loginByAccountAndPassword(@RequestParam int user_id) throws Exception{
        User resultUser = new User();
        UserApiVo userApiVo = new UserApiVo();

        userApiVo.setMessage("说明：");

        //判断数据合法性
        if (user_id <= 0){
            //模拟数据非法
            userApiVo.setMessage(userApiVo.getMessage() + "user_id数据不合法！");
            userApiVo.setCode(0);
            userApiVo.setUser(null);
        } else {
            //数据合法
            userApiVo = userService.getUserById(user_id);
        }

        return ApiFormatUtil.apiFormat(userApiVo.getCode(),userApiVo.getMessage(),userApiVo.getUser());
    }


}
