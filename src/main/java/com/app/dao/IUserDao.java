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


    /**
     * 通过用户电话号码查找用户信息
     * @param telphone  用户电话号码
     * @return  User用户全部基本信息
     * @throws Exception
     */
    public User findUserByTelphone(String telphone) throws Exception;


    /**
     * 通过username和password查找用户信息
     * @param user  用户对象，其中username和password必须要填充数据值
     * @return  User用户全部基本信息
     * @throws Exception
     */
    public User findUserByUsernameAndPassword(User user) throws Exception;


    /**
     * 通过用户名和密码验证用户是否存在(返回所查询的记录数)
     * @param user  User中填充username和password两个字段
     * @return  在数据库中所查询到的记录数
     * @throws Exception
     */
    public int matchByUsernameAndPassword(User user) throws Exception;


    /**
     * 通过电话号码和密码验证用户是否存在(返回所查询的记录数)
     * @param user  User中填充telphone和password两个字段
     * @return  在数据库中所查询到的记录数
     * @throws Exception
     */
    public int matchByTelphoneAndPassword(User user) throws Exception;


    /**
     * 注册用户
     * @param user  用户基本信息
     * @throws Exception
     */
    public void insertUser(User user) throws Exception;


    /**
     * 查询数据库中利用参数telphone注册的用户是否存在
     * @param telphone  用户电话号码
     * @return  查询所得的记录数，即利用此号码进行注册的用户数
     * @throws Exception
     */
    public int queryTelphone(String telphone) throws Exception;


    /**
     * 修改密码
     * @param user  需要填充用户ID和用户新密码
     * @throws Exception
     */
    public void modifyPassword(User user) throws Exception;


    /**
     * 修改用户名
     * @param user  需要填充用户ID和用户新用户名
     * @throws Exception
     */
    public void modifyUsername(User user) throws Exception;


    /**
     * 修改用户头像路径
     * @param user  需要填充用户ID和用户新头像路径
     * @throws Exception
     */
    public void modifyHead_portrail(User user) throws Exception;


    /**
     * 修改用户性别
     * @param user 需要填充用户ID和用户新性别
     * @throws Exception
     */
    public void modifyGender(User user) throws Exception;


    /**
     * 修改用户出生日期
     * @param user 需要填充用户ID和用户新出生日期
     * @throws Exception
     */
    public void modifyBirthday(User user) throws Exception;


    /**
     * 修改用户备注
     * @param user  需要填充用户ID和用户新介绍、备注
     * @throws Exception
     */
    public void modifyIntroduce(User user) throws Exception;


    /**
     * 修改用户电话号码
     * @param user  需要填充用户ID和用户新电话号码
     * @throws Exception
     */
    public void modifyTelphone(User user) throws Exception;


    /**
     * 修改经度
     * @param user 需要填充用户ID和经度
     * @throws Exception
     */
    public void modifyLongitude(User user) throws Exception;


    /**
     * 修改纬度
     * @param user 需要填充用户ID和纬度
     * @throws Exception
     */
    public void modifyLatitude(User user) throws Exception;





}
