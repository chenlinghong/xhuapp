package com.app.dao;

import com.app.entity.User;

/**
 * @program: xhuapp
 * @description: tb_user表相关dao类
 * @author: Mr.Chen
 * @create: 2018-03-22 19:41
 **/

public interface IUserDao {

    /**
     * 通过用户ID查询用户基本信息
     * @param user_id
     * @return 用户信息
     * @throws Exception
     */
    public User findUserByUser_id(int user_id) throws Exception;

}
