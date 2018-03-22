package com.app.service;

import com.app.vo.UserApiVo;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: busis
 * @description: 对于User相关的service层接口
 * @author: Mr.Chen
 * @create: 2018-03-13 13:10
 **/

@Transactional
public interface IUserService {

    /**
     * 通过用户ID查询用户基本信息
     * @param user_id
     * @return  code: 查询结果（1：成功，0：失败）
     *          user: 用户基本信息
     *          message: 查询结果描述
     * @throws Exception
     */
    public UserApiVo getUserById(int user_id) throws Exception;



}
