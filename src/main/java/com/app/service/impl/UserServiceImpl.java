package com.app.service.impl;

import com.app.dao.IUserDao;
import com.app.entity.User;
import com.app.enums.ResultEnum;
import com.app.exception.BaseException;
import com.app.service.IUserService;
import com.app.util.TelphoneCheckUtil;
import com.app.vo.UserApiVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public UserServiceImpl() {

    }

    public UserApiVo loginByAccountAndPassword(String account, String password) throws Exception {
        User user = new User();
        int result = 0;         //返回结果
        int records = 0;        //数据库返回记录数

        userApiVo.setMessage("");
        userApiVo.setUser(null);

        //验证用户传入帐号是否为电话号码
        if (TelphoneCheckUtil.isPhoneLegal(account)) {
            //表明account为合法电话号码字符串
            user.setTelphone(account);
            user.setPassword(password);
            records = userDao.matchByTelphoneAndPassword(user);
            if (records > 0) {
                //表示用户存在，即登录成功
                result = 1;
                userApiVo.setCode(1);
                userApiVo.setMessage("登录成功");
            } else {
                //表示通过电话号码和密码进行登录失败
                result = 0;
                userApiVo.setCode(0);
                userApiVo.setMessage("电话号码或密码错误！");
            }
        } else {
            //表明account不是合法电话号码，这里我们默认用户输入的为用户名
            user.setUsername(account);
            user.setPassword(password);
            records = userDao.matchByUsernameAndPassword(user);
            if (records > 0) {
                //表示用户存在，即登录成功
                result = 2;
                userApiVo.setCode(2);
                userApiVo.setMessage(userApiVo.getMessage() + "登录成功");
            } else {
                //用户通过用户名和密码进行登录失败
                userApiVo.setCode(0);
                userApiVo.setMessage(userApiVo.getMessage() + "用户名或密码错误");
            }
        }

        return userApiVo;
    }

    public UserApiVo getUserByTelphone(String telphone) throws Exception {
        userApiVo.setCode(0);
        userApiVo.setMessage("");
        userApiVo.setUser(null);

        User user = userDao.findUserByTelphone(telphone);
        if (user == null) {
            //通过电话号码未查询到用户
            userApiVo.setCode(0);
            userApiVo.setMessage(userApiVo.getMessage() + "用户信息不存在。");
            userApiVo.setUser(null);
        } else {
            //查询到用户信息
            userApiVo.setCode(1);
            userApiVo.setMessage(userApiVo.getMessage() + "获取信息成功。");
            userApiVo.setUser(user);
        }

        return userApiVo;
    }

    public UserApiVo getUserByUsernameAndPassword(String username, String password) throws Exception {
        User temp = new User();
        userApiVo.setCode(0);
        userApiVo.setMessage("");
        userApiVo.setUser(null);

        //设置用户名和密码，使其满足userDao中方法的参数要求
        temp.setUsername(username);
        temp.setPassword(password);

        User result = userDao.findUserByUsernameAndPassword(temp);
        if (result == null) {
            //通过用户名和密码未查询到用户
            userApiVo.setCode(0);
            userApiVo.setMessage(userApiVo.getMessage() + "用户信息不存在。");
            userApiVo.setUser(null);
        } else {
            //查询到用户信息
            userApiVo.setCode(1);
            userApiVo.setMessage(userApiVo.getMessage() + "获取信息成功。");
            userApiVo.setUser(result);
        }
        return userApiVo;
    }

    public UserApiVo getUserByUser_id(int user_id) throws Exception {
        User result = userDao.findUserByUser_id(user_id);
        if (result == null) {
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

    @Transactional
    public UserApiVo registerUser(User user) throws Exception {
        try {
            int recordsTelphone = 0;
            //验证数据库中是否存在利用该电话号码的用户
            recordsTelphone = userDao.queryTelphone(user.getTelphone());
            if (recordsTelphone > 0) {
                //电话号码不可用
                userApiVo.setCode(0);
                userApiVo.setMessage("此电话号码已注册。");
                userApiVo.setUser(user);
            } else {
                userDao.insertUser(user);
                userApiVo.setCode(1);
                userApiVo.setMessage("注册成功");
                userApiVo.setUser(user);
            }
        } catch (Exception e){
            throw new BaseException(ResultEnum.UNKNOWN_ERROR);
        }
        return userApiVo;
    }

//    public UserApiVo updateUserInformation(User user) throws Exception {
//        User tempUser = new User();
//        boolean result = true;
//
//        //对结果进行初始化
//        userApiVo.setMessage("");
//        userApiVo.setUser(null);
//
//        if (user == null){
//            result = false;
//            userApiVo.setMessage(userApiVo.getMessage() + "获取参数失败！");
//            userApiVo.setCode(0);
//            userApiVo.setUser(null);
//        } else {
//            //检验数据合法性
//            if (user.getUsername() == "" || user.getUsername() == null){
//                result = false;
//                userApiVo.setMessage(userApiVo.getMessage() + "用户名为空！");
//            } else {
//                if (user.getUsername().length() > 10){
//                    result = false;
//                    userApiVo.setMessage(userApiVo.getMessage() + "用户名长度应小于10！");
//                } else {
//                    user.setUsername(user.getUsername().trim());
//                }
//            }
//            if (user.getTelphone() != null && user.getTelphone() != ""){
//                if(!TelphoneCheckUtil.isPhoneLegal(user.getTelphone())){
//                    result = false;
//                    userApiVo.setMessage(userApiVo.getMessage() + "电话号码不合法！");
//                }
//            } else {
//                //TODO
//
//            }
//
//            if (user.getHead_portrail() == "" || user.getHead_portrail() == null){
//                result = false;
//                userApiVo.setMessage(userApiVo.getMessage() + "头像信息为空！");
//            } else if (user.getHead_portrail().length() > 100){
//                result = false;
//                userApiVo.setMessage(userApiVo.getMessage() + "头像信息长度应小于100！");
//            } else {
//                user.setHead_portrail(user.getHead_portrail().trim());
//            }
//            if (user.getPassword() == "" || user.getPassword() == null){
//                result = false;
//                userApiVo.setMessage(userApiVo.getMessage() + "密码数据为空！");
//            } else if (user.getPassword().length() > 20){
//                result = false;
//                userApiVo.setMessage(userApiVo.getMessage() + "密码数据过长！");
//            } else {
//                user.setPassword(user.getPassword().trim());
//            }
//            if (user.getGender() != ' ' && user.getGender() != '1' && user.getGender() != '0'){
//                result = false;
//                userApiVo.setMessage(userApiVo.getMessage() + "性别信息不合法！");
//            }
//            if (user.getIntroduce() == "" || user.getIntroduce() == null){
//                result = false;
//                userApiVo.setMessage(userApiVo.getMessage() + "用户介绍信息为空！");
//            } else if (user.getIntroduce().length() > 200){
//                result = false;
//                userApiVo.setMessage(userApiVo.getMessage() + "用户介绍信息过长！");
//            } else {
//                user.setIntroduce(user.getIntroduce().trim());
//            }
//            if (user.getLongitude() == "" || user.getLongitude() == null){
//                result = false;
//                userApiVo.setMessage(userApiVo.getMessage() + "经度为空！");
//            } else if (user.getLongitude().length() > 30){
//                result = false;
//                userApiVo.setMessage(userApiVo.getMessage() + "经度不能超过30个字符！");
//            } else {
//                user.setLongitude(user.getLongitude().trim());
//            }
//            if (user.getLatitude() == "" || user.getLatitude() == null){
//                result = false;
//                userApiVo.setMessage(userApiVo.getMessage() + "纬度为空！");
//            } else if (user.getLatitude().length() > 30){
//                result = false;
//                userApiVo.setMessage(userApiVo.getMessage() + "纬度不能超过30个字符！");
//            } else {
//                user.setLatitude(user.getLatitude().trim());
//            }
//
//            if (result == true){
//                //通过ID查询是否存在该用户
//                User queryUser = userDao.findUserByUser_id(user.getUser_id());
//                if(queryUser == null){
//                    //数据库中不存在此用户
//                    result = false;
//                    userApiVo.setMessage(userApiVo.getMessage() + "用户不存在！");
//                } else {
//                    //填充数据（保护原有数据）
//                    tempUser.setUser_id(user.getUser_id());
//                    tempUser.setUsername(user.getUsername());
//                    tempUser.setTelphone(user.getTelphone());
//                    tempUser.setBirthday(user.getBirthday());
//                    tempUser.setHead_portrail(user.getHead_portrail());
//                    tempUser.setGender(user.getGender());
//                    tempUser.setPassword(user.getPassword());
//                    tempUser.setIntroduce(user.getIntroduce());
//                    tempUser.setLongitude(user.getLongitude());
//                    tempUser.setLatitude(user.getLatitude());
//
//                    //校验是否修改电话号码
//                    if (tempUser.getTelphone() != null && tempUser.getTelphone() != ""){
//                        //用户欲修改电话号码
//                        //需要进行判断是否能够修改成功
//                        int records = -1;       //记录数
//                        records = userDao.queryTelphone(tempUser.getTelphone());
//                        if (records > 0){
//                            //说明数据库中存在此号码
//                            result = false;
//                            userApiVo.setCode(0);
//                            userApiVo.setMessage(userApiVo.getMessage() + "修改电话号码失败！");
//                        } else {
//                            //说明可以进行电话号码的修改
//                            //修改电话号码
//                            userApiVo.setCode(1);
//                            userDao.modifyTelphone(tempUser);
//                        }
//                    }
//                    if (result == true){
//                        //判断是否进行各项信息的修改，并进行修改
//                        //修改用户名
//                        if (tempUser.getUsername() != "" && tempUser.getUsername() != null){
//                            userDao.modifyUsername(tempUser);
//                        }
//                        //修改出生日期
//                        if (tempUser.getBirthday() != null){
//                            userDao.modifyBirthday(tempUser);
//                        }
//                        //修改头像路径
//                        if (tempUser.getHead_portrail() != "" && tempUser.getHead_portrail() != null){
//                            userDao.modifyHead_portrail(tempUser);
//                        }
//                        //修改用户性别
//                        if (tempUser.getGender() == '0' || tempUser.getGender() == '1'){
//                            userDao.modifyGender(tempUser);
//                        }
//                        //修改密码
//                        if (tempUser.getPassword() != "" && tempUser.getPassword() != null){
//                            userDao.modifyPassword(tempUser);
//                        }
//                        //修改备注
//                        if (tempUser.getIntroduce() != "" && tempUser.getIntroduce() != null){
//                            userDao.modifyIntroduce(tempUser);
//                        }
//                        //修改经度
//                        if (tempUser.getLongitude() != "" && tempUser.getLongitude() != null){
//                            userDao.modifyLongitude(tempUser);
//                        }
//                        //修改纬度
//                        if (tempUser.getLatitude() != "" && tempUser.getLatitude() != null){
//                            userDao.modifyLatitude(tempUser);
//                        }
//
//                        //执行到此步说明更改用户信息成功
//                        userApiVo.setMessage(userApiVo.getMessage() + "修改用户信息成功！");
//                        userApiVo.setCode(1);
//                    }
//                }
//            } else{
//                //存在不合法信息
//                userApiVo.setCode(0);
//                userApiVo.setUser(null);
//            }
//        }
//
//        return userApiVo;
//    }


    @Transactional
    public UserApiVo updateUserInformation(User user) throws Exception {
        User tempUser = new User();
        boolean result = true;

        UserApiVo userApiVo = new UserApiVo();

        //对结果进行初始化
        userApiVo.setMessage("");
        userApiVo.setUser(null);

        //检验数据合法性
        if (user.getUsername() != "" && user.getUsername() != null) {
            if (user.getUsername().length() > 10) {
                result = false;
                userApiVo.setMessage(userApiVo.getMessage() + "用户名不超过10个字符！");
            } else if (user.getUsername().length() < 1) {
                result = false;
                userApiVo.setMessage(userApiVo.getMessage() + "用户名不能为一个字符！");
            }
        }
        if (user.getTelphone() != null && user.getTelphone() != "") {
            if (user.getTelphone().length() != 11) {
                result = false;
                userApiVo.setMessage(userApiVo.getMessage() + "请输入有效电话号码！");
            }
            if (!TelphoneCheckUtil.isPhoneLegal(user.getTelphone())) {
                result = false;
                userApiVo.setMessage(userApiVo.getMessage() + "电话号码不合法！");
            }
        }


        if (user.getPassword() != "" && user.getPassword() != null) {
            if (user.getPassword().length() > 20 || user.getPassword().length() < 6) {
                result = false;
                userApiVo.setMessage(userApiVo.getMessage() + "密码须为6至20个字符！");
            }
        }
//        if (user.getGender() != ' ' && user.getGender() != '1' && user.getGender() != '0'){
//            result = false;
//            userApiVo.setMessage(userApiVo.getMessage() + "性别信息不合法！");
//        }
        if (user.getIntroduce() != "" && user.getIntroduce() != null) {
            if (user.getIntroduce().length() > 200) {
                result = false;
                userApiVo.setMessage(userApiVo.getMessage() + "用户介绍信息不能超过200个字符！");
            }
        }
        if (user.getHead_portrail() != "" && user.getHead_portrail() != null) {
            if (user.getHead_portrail().length() > 100) {
                result = false;
                userApiVo.setMessage(userApiVo.getMessage() + "头像信息长度过长！");
            } else {
                user.setHead_portrail(user.getHead_portrail().trim());
            }

            if (result) {
                //通过ID查询是否存在该用户
                User queryUser = userDao.findUserByUser_id(user.getUser_id());
                if (queryUser == null) {
                    //数据库中不存在此用户
                    result = false;
                    userApiVo.setMessage(userApiVo.getMessage() + "用户不存在！");
                } else {
                    //填充数据（保护原有数据）
                    tempUser.setUser_id(user.getUser_id());
                    tempUser.setUsername(user.getUsername());
                    tempUser.setTelphone(user.getTelphone());
                    tempUser.setBirthday(user.getBirthday());
                    tempUser.setGender(user.getGender());
                    tempUser.setPassword(user.getPassword());
                    tempUser.setIntroduce(user.getIntroduce());
                    tempUser.setHead_portrail(user.getHead_portrail());

                    try {

                        //校验是否修改电话号码
                        if (tempUser.getTelphone() != null && tempUser.getTelphone() != "") {
                            if (tempUser.getTelphone().equals(queryUser.getTelphone())) {
                                //用户未进行电话号码修改

                            } else {
                                //用户欲修改电话号码
                                //需要进行判断是否能够修改成功
                                int records = -1;       //记录数
                                records = userDao.queryTelphone(tempUser.getTelphone());
                                if (records > 0 && !queryUser.getTelphone().equals(tempUser.getTelphone())) {
                                    //说明数据库中存在此号码
                                    result = false;
                                    userApiVo.setCode(0);
                                    userApiVo.setMessage(userApiVo.getMessage() + "电话号码已注册！");
                                } else {
                                    //说明可以进行电话号码的修改
                                    //修改电话号码
                                    result = true;
                                    userDao.modifyTelphone(tempUser);
                                }
                            }
                        }
                        if (result) {
                            //判断是否进行各项信息的修改，并进行修改
                            //修改用户名
                            if (tempUser.getUsername() != "" && tempUser.getUsername() != null) {
                                userDao.modifyUsername(tempUser);
                            }
                            //修改出生日期
                            if (tempUser.getBirthday() != null) {
                                userDao.modifyBirthday(tempUser);
                            }

                            //修改用户性别
                            if ((tempUser.getGender() == '0' || tempUser.getGender() == '1')
                                    && tempUser.getGender() != queryUser.getGender()) {
                                userDao.modifyGender(tempUser);
                            }
                            //修改密码
                            if (tempUser.getPassword() != "" && tempUser.getPassword() != null
                                    && !tempUser.getPassword().equals(queryUser.getPassword())) {
                                userDao.modifyPassword(tempUser);
                            }
                            //修改备注
                            if (tempUser.getIntroduce() != "" && tempUser.getIntroduce() != null
                                    && !tempUser.getIntroduce().equals(queryUser.getIntroduce())) {
                                userDao.modifyIntroduce(tempUser);
                            }
                            //修改头像路径
                            if (tempUser.getHead_portrail() != "" && tempUser.getHead_portrail() != null
                                    && !tempUser.getHead_portrail().equals(queryUser.getHead_portrail())) {
                                userDao.modifyHead_portrail(tempUser);
                            }

                            //执行到此步说明更改用户信息成功
                            userApiVo.setMessage(userApiVo.getMessage() + "修改用户信息成功！");
                            userApiVo.setCode(1);
                            userApiVo.setUser(userDao.findUserByUser_id(user.getUser_id()));
                        }
                    } catch (Exception e){
                        throw new BaseException(ResultEnum.UNKNOWN_ERROR);
                    }
                }
            } else {
                //存在不合法信息
                userApiVo.setCode(0);
                userApiVo.setUser(null);
            }
        }
        return userApiVo;
    }
}
