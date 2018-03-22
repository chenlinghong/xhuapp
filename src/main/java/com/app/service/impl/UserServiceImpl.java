package com.app.service.impl;

import com.app.dao.IUserDao;
import com.app.entity.User;
import com.app.service.IUserService;
import com.app.vo.UserApiVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: busis
 * @description: IUserService接口实现类
 * @author: Mr.Chen
 * @create: 2018-03-13 13:10
 **/

@Service(value = "userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Resource
    private UserApiVo userApiVo;

    //无参构造
    public UserServiceImpl(){

    }



    public UserApiVo getUserById(int user_id) throws Exception {
        User result = userDao.findUserByUser_id(user_id);
        if (result == null){
            //通过ID未查询到用户
            userApiVo.setCode(0);
            userApiVo.setMessage("用户信息不存在。");
            userApiVo.setUser(null);
        } else {
            //查询到用户信息
            userApiVo.setCode(1);
            userApiVo.setMessage("获取信息成功。");
            userApiVo.setUser(result);
        }
        return userApiVo;
    }


}
