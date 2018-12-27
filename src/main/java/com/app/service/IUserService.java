package com.app.service;

import com.app.entity.User;
import com.app.vo.UserApiVo;

/**
 * @program: busis
 * @description: 对于User相关的service层接口
 * @author: Mr.Chen
 * @create: 2018-03-13 13:10
 **/

public interface IUserService {

    /**
     * @param account 用户账户（电话号码或用户名）
     * @param password  用户密码
     * @return 登录结果(code  0:登录失败，1;电话号码登录成功，2：用户名登录成功)
     *                  (message  登录结果描述)
     * @throws Exception
     */
    public UserApiVo loginByAccountAndPassword(String account, String password) throws Exception;


    /**
     * 通过电话号码获得用户基本信息
     * @param telphone  用户电话号码
     * @return code: 查询结果（1：成功，0：失败）
     *          user: 用户基本信息
     *          message: 查询结果描述
     * @throws Exception
     */
    public UserApiVo getUserByTelphone(String telphone) throws Exception;


    /**
     * 通过用户名和密码获取用户基本信息
     * @param username  用户名
     * @param password  密码
     * @return code: 查询结果（1：成功，0：失败）
     *          user: 用户基本信息
     *          message: 查询结果描述
     * @throws Exception
     */
    public UserApiVo getUserByUsernameAndPassword(String username,String password) throws Exception;


    /**
     * 通过用户ID查询用户基本信息
     * @param user_id
     * @return  code: 查询结果（1：成功，0：失败）
     *          user: 用户基本信息
     *          message: 查询结果描述
     * @throws Exception
     */
    public UserApiVo getUserByUser_id(int user_id) throws Exception;

    /**
     * 注册用户
     * @param user  用户对象，其中user_id字段为空
     * @return  code: 注册结果（1：成功，0：失败）
     *          user: 用户基本信息
     *          message: 结果描述
     * @throws Exception
     */
    public UserApiVo registerUser(User user) throws Exception;


    /**
     * 修改用户基本信息
     * @param user
     *          user_id: 用户ID
     *          username: 用户名
     *          telphone: 用户电话号码
     *          birthday: 用户出生日期
     *          head_portrail: 用户头像路径
     *          gender: 用户性别
     *          password: 用户密码
     *          introduce：用户备注
     * @return  code: 结果（1：成功，0：失败）
     *          message: 结果描述
     * @throws Exception
     */
    public UserApiVo updateUserInformation(User user) throws Exception;



}
