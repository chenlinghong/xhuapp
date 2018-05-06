package com.app.controller;

import com.app.dto.LoginVo;
import com.app.dto.RecoverPasswordDto;
import com.app.dto.UserDto;
import com.app.entity.User;
import com.app.service.IAttentionService;
import com.app.service.IUserService;
import com.app.util.*;
import com.app.vo.ModifyUserVo;
import com.app.vo.SmsVo;
import com.app.vo.UserApiVo;
import com.app.vo.UserBaseInformationVo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

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

    @Autowired
    private IAttentionService attentionService;


    //无参构造
    public UserController() {

    }

    /**
     * 通过用户账户（电话号码/用户名）进行登录验证并返回用户基本信息
     *
     * @param loginVo
     *          account : 用户账户（电话号码）
     *          password :  用户密码
     * @return 用户信息JSON字符串
     * user_id：用户登录成功返回用户ID，登录失败返回-1
     * username：用户姓名
     * password：用户密码（鉴于安全，一般为空）
     * gender：用户性别
     * birthday：用户出生日期
     * head_portrail：用户头像图片路径
     * introduce：用户介绍
     * telphone：用户电话号码
     * （注：若登录失败，user_id为-1，其余参数为默认值）
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String loginByAccountAndPassword(LoginVo loginVo) throws Exception {
        String account = "";
        String password = "";
        User resultUser = new User();
        UserApiVo userApiVo = new UserApiVo();
        userApiVo.setCode(1);
        userApiVo.setMessage("");

        UserApiVo loginResult = new UserApiVo();

        //判别输入参数
        if (loginVo == null){
            userApiVo.setCode(0);
            userApiVo.setMessage(userApiVo.getMessage() + "未接收到参数！");
        } else {
            account = loginVo.getAccount();
            password = loginVo.getPassword();

            if (account == null || account == ""){
                userApiVo.setCode(0);
                userApiVo.setMessage(userApiVo.getMessage() + "用户账户为空!");
            } else {
                account = account.trim();
            }

            if (password == null || password == "") {
                userApiVo.setCode(0);
                userApiVo.setMessage(userApiVo.getMessage() + "密码为空！");
            } else {
                password = password.trim();
            }

            if (userApiVo.getCode() == 1){
                //参数输入正确
                loginResult = userService.loginByAccountAndPassword(account, password);
                loginResult.setMessage(userApiVo.getMessage() + loginResult.getMessage());

                //判断loginResult对象
                if (loginResult == null){
                    //结果返回出错
                    loginResult = new UserApiVo();
                    loginResult.setCode(0);
                    loginResult.setMessage("请求失败！");
                } else {
                    if (loginResult.getCode() == 1) {
                        //表示利用电话号码和密码进行成功登录
                        resultUser = userService.getUserByTelphone(account).getUser();
                    } else if (loginResult.getCode() == 2) {
                        //表示通过用户名和密码进行成功登录
                        resultUser = userService.getUserByUsernameAndPassword(account, password).getUser();
                        loginResult.setCode(1);
                    } else {
                        //登录失败
                        resultUser.setUser_id(-1);
                        loginResult.setCode(0);
                    }
                }
            }
        }

        String resultJson = "";

        if (loginResult.getCode() == 0){

        } else {
            resultUser = SimpleUtil.hideSensitiveInformation(resultUser);


            GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.setPrettyPrinting();        //格式化（仅用于开发阶段）
            gsonBuilder.setDateFormat("yyyy-MM-dd");
            Gson gson = gsonBuilder.create();

//            resultJson = gson.toJson(resultUser);

            //获取用户关注数和粉丝数
            int fans = 0, attention = 0;
            try {
                fans = attentionService.getFansNum(resultUser.getUser_id());
                attention = attentionService.getAttentionNum(resultUser.getUser_id());
            } catch (Exception e){
                throw e;
            }

            UserDto userDto = new UserDto();
            userDto.setUser_id(resultUser.getUser_id());
            userDto.setUsername(resultUser.getUsername());
            userDto.setTelphone(resultUser.getTelphone());
            userDto.setBirthday(resultUser.getBirthday());
            userDto.setHead_portrail(resultUser.getHead_portrail());
            userDto.setGender(resultUser.getGender());
            userDto.setPassword(resultUser.getPassword());
            userDto.setIntroduce(resultUser.getIntroduce());
            userDto.setLongitude(resultUser.getLongitude());
            userDto.setLatitude(resultUser.getLatitude());
            userDto.setFans(fans);
            userDto.setAttention(attention);

            resultJson = gson.toJson(userDto);
        }

        return ApiFormatUtil.apiFormat(loginResult.getCode(), loginResult.getMessage(), resultJson);
    }


    @RequestMapping(value = "/find", method = {RequestMethod.POST, RequestMethod.GET},
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getUserByUser_id(int user_id) {
        UserApiVo userApiVo = new UserApiVo();
        userApiVo.setCode(1);
        userApiVo.setMessage("");
        userApiVo.setUser(null);

        try {
            userApiVo = userService.getUserByUser_id(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            userApiVo.setCode(0);
            userApiVo.setMessage("未知错误");
        }
        String resultJson = "";

        if (userApiVo.getCode() == 0){

        } else {
            User user = SimpleUtil.hideSensitiveInformation(userApiVo.getUser());

            GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.setPrettyPrinting();        //格式化（仅用于开发阶段）
            gsonBuilder.setDateFormat("yyyy-MM-dd");
            Gson gson = gsonBuilder.create();

            resultJson = gson.toJson(user);
        }

        return ApiFormatUtil.apiFormat(userApiVo.getCode(), userApiVo.getMessage(), resultJson);
    }


    /**
     * 用户注册
     *
     * @param userBaseInformationVo username：用户姓名
     *                              password：用户密码
     *                              telphone：用户电话号码
     *                              gender：用户性别
     *                              birthday: 用户出生日期（yyyy-MM-dd）
     *                              introduce: 用户备注
     * @return 用户基本信息
     * user_id：用户登录成功返回用户ID，登录失败返回-1
     * username：用户姓名
     * password：用户密码（鉴于安全，一般为空）
     * gender：用户性别
     * birthday：用户出生日期
     * head_portrail：用户头像图片路径
     * introduce：用户介绍
     * telphone：用户电话号码
     * （注：若注册成功，返回值均为数据库中查询当前注册用户所得。若注册失败，user_id为-1，其他值为原值）
     */
    @RequestMapping(value = "/register", method = {RequestMethod.POST, RequestMethod.GET},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String registerUser(UserBaseInformationVo userBaseInformationVo,
                               HttpServletRequest request,HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        User tempUser = new User();
        User resultUser = new User();
        boolean check = true;
        int resultId = -1;
        UserApiVo userApiVo = new UserApiVo();

        //初始化结果说明
        userApiVo.setMessage("");

        //判断短信验证码是否有效
        String smsCode = (String) session.getAttribute("code");
        String smsTelphone = (String) session.getAttribute("telphone");

        if (smsCode != null && smsCode != ""){
            smsCode = smsCode.trim();
        }
        if (smsTelphone != null && smsTelphone != ""){
            smsTelphone = smsTelphone.trim();
        }


        String telphone = userBaseInformationVo.getTelphone();

        if (telphone == null || telphone == ""){
            check = false;
            userApiVo.setMessage(userApiVo.getMessage() + "电话号码不能为空！");
        } else {
            telphone = telphone.trim();
        }

        if (check){
            if (userBaseInformationVo.getCode() == "" || userBaseInformationVo.getCode() == null){
                //验证失败
                check = false;
                userApiVo.setMessage(userApiVo.getMessage() + "未输入短信验证码！");
            } else if (!telphone.equals(smsTelphone)){
                //验证失败
                check = false;
                userApiVo.setMessage(userApiVo.getMessage() + "手机号码错误！");
            } else if (!userBaseInformationVo.getCode().equals(smsCode)){
                //验证失败
                check = false;
                userApiVo.setMessage(userApiVo.getMessage() + "短信验证码错误！");
            } else {
                //验证成功
                //必填项检查
                if (userBaseInformationVo.getUsername() == "" || userBaseInformationVo.getUsername() == null) {
                    check = false;
                    userApiVo.setMessage(userApiVo.getMessage() + "用户名不能为空！");
                }
                if (userBaseInformationVo.getTelphone() == "" || userBaseInformationVo.getTelphone() == null) {
                    check = false;
                    userApiVo.setMessage(userApiVo.getMessage() + "电话号码不能为空！");
                }
                if (userBaseInformationVo.getPassword() == "" || userBaseInformationVo.getPassword() == null) {
                    check = false;
                    userApiVo.setMessage(userApiVo.getMessage() + "密码不能为空！");
                }

                if (check == false) {
                    //缺少必填项
                    userApiVo.setCode(0);
                } else {
                    //填充数据，并检验数据合法性
                    //检验username长度
                    if (userBaseInformationVo.getUsername().length() < 10) {
                        tempUser.setUsername(userBaseInformationVo.getUsername());
                    } else {
                        //username数据长度不合法
                        check = false;
                        userApiVo.setMessage(userApiVo.getMessage() + "用户名不能超过10个字符！");
                    }
                    //验证手机号码是否合法
                    if (TelphoneCheckUtil.isPhoneLegal(userBaseInformationVo.getTelphone())) {
                        tempUser.setTelphone(userBaseInformationVo.getTelphone());
                    } else {
                        check = false;
                        userApiVo.setMessage(userApiVo.getMessage() + "电话号码不正确！");
                    }
                    //检验密码长度是否合法
                    if (userBaseInformationVo.getPassword().length() < 20) {
                        tempUser.setPassword(userBaseInformationVo.getPassword());
                    } else {
                        //数据不合法
                        check = false;
                        userApiVo.setMessage(userApiVo.getMessage() + "密码长度不能超过20个字符！");
                    }

                    //判断必填项是否合法结果
                    if (check == false) {
                        //数据非法
                        userApiVo.setCode(0);

                    } else {
                        //必填项合法，填充其他信息
                        if (userBaseInformationVo.getBirthday() != null && userBaseInformationVo.getBirthday() != null) {
                            //将字符串转换为Date
                            tempUser.setBirthday(GetDateByStringUtils.getDate(userBaseInformationVo.getBirthday()));
                        }
                        if (userBaseInformationVo.getGender() != "" && userBaseInformationVo.getGender() != null) {
//                    tempUser.setGender((char) user.getGender().indexOf(1));
                            if (userBaseInformationVo.getGender().equals("1")) {
                                tempUser.setGender('1');
                            } else {
                                tempUser.setGender('0');
                            }
                        } else {
                            //用户注册时未填写性别
                            tempUser.setGender(' ');
                        }
                        //填充用户介绍信息
                        if (userBaseInformationVo.getIntroduce() != "" && userBaseInformationVo.getIntroduce() != null) {
                            tempUser.setIntroduce(userBaseInformationVo.getIntroduce());
                        }
                        //经纬度
                        if (userBaseInformationVo.getLongitude() != "" && userBaseInformationVo.getLongitude() != null) {
                            tempUser.setLongitude(userBaseInformationVo.getLongitude());
                        }
                        if (userBaseInformationVo.getLatitude() != "" && userBaseInformationVo.getLatitude() != null) {
                            tempUser.setLatitude(userBaseInformationVo.getLatitude());
                        }
                    }
                }

                if (check == false) {
                    //存在不合法数据
                    userApiVo.setCode(0);

                    resultId = -1;      //标识注册失败
                    resultUser.setUser_id(resultId);
                    resultUser.setUsername(userBaseInformationVo.getUsername());
                    resultUser.setIntroduce(userBaseInformationVo.getIntroduce());
//            resultUser.setGender((char) user.getGender().indexOf(1));
                    if (userBaseInformationVo.getGender() != null) {
                        if (userBaseInformationVo.getGender().equals("1")) {
                            tempUser.setGender('1');
                        } else {
                            tempUser.setGender('0');
                        }
                    }
                    if (userBaseInformationVo.getBirthday() != null) {
                        resultUser.setBirthday(GetDateByStringUtils.getDate(userBaseInformationVo.getBirthday()));
                    }
                    resultUser.setPassword(userBaseInformationVo.getPassword());
                    resultUser.setTelphone(userBaseInformationVo.getTelphone());
                } else {
                    //进行注册操作
                    userApiVo = userService.registerUser(tempUser);
                    if (userApiVo.getCode() == 0) {
                        //注册失败

                    } else {
                        //注册成功
                        //重新从数据库中查询数据
                        UserApiVo userApiVo1 = userService.getUserByUser_id(userApiVo.getUser().getUser_id());
                        if (userApiVo1.getCode() == 0){
                            check = false;
                            userApiVo.setMessage(userApiVo.getMessage() + userApiVo1.getMessage());
                        } else {
                            resultUser = userApiVo1.getUser();
                            userApiVo.setMessage("注册成功！");
                            check = true;
                        }
                    }
                }
            }
        }



        String resultJson = "";

        if (check == false){

        }else {
            GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.setPrettyPrinting();        //格式化（仅用于开发阶段）
            gsonBuilder.setDateFormat("yyyy-MM-dd");
            Gson gson = gsonBuilder.create();

            resultUser = SimpleUtil.hideSensitiveInformation(resultUser);

            resultJson = gson.toJson(resultUser);
        }


        return ApiFormatUtil.apiFormat(userApiVo.getCode(), userApiVo.getMessage(), resultJson);
    }


    /**
     * 修改用户基本信息
     *
     * @param modifyUserVo user_id: 用户ID
     *                     username：用户姓名
     *                     password：用户密码
     *                     telphone：用户电话号码
     *                     gender：用户性别
     *                     birthday: 用户出生日期（yyyy-MM-dd）
     *                     introduce: 用户备注
     * @return 修改结果布尔值（修改成功返回true，反之为false）
     * @throws Exception
     */
    @RequestMapping(value = "/modify", method = {RequestMethod.POST, RequestMethod.GET},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String modifyUserBaseInformation(ModifyUserVo modifyUserVo) throws Exception {
//        User user = new User();
//        boolean result = false;
//        UserApiVo userApiVo = new UserApiVo();
//
//        userApiVo.setMessage("");
//
//        user.setUser_id(modifyUserVo.getUser_id());
//        user.setUsername(modifyUserVo.getUsername());
//        user.setPassword(modifyUserVo.getPassword());
//        if (modifyUserVo.getGender() != "" && modifyUserVo.getGender() != null){
//            if (modifyUserVo.getGender().equals("1")) {
//                user.setGender('1');
//            }
//        }else {
//            user.setGender('0');
//        }
//        if (modifyUserVo.getBirthday() != "" && modifyUserVo.getBirthday() != null){
//            user.setBirthday(GetDateByStringUtils.getDate(modifyUserVo.getBirthday()));
//        } else {
//            //TODO
//
//        }
//        user.setIntroduce(modifyUserVo.getIntroduce());
//        user.setTelphone(modifyUserVo.getTelphone());
//
//        userApiVo = userService.updateUserInformation(user);
//
//        return ApiFormatUtil.apiFormat(userApiVo.getCode(), userApiVo.getMessage(), "");

        User user = new User();
        User resultUser = new User();
        boolean result = false;
        UserApiVo userApiVo = new UserApiVo();
        String resultJson = "";

//        userApiVo.setMessage("说明：");

        if(modifyUserVo.getUser_id() <= 0){
            //user_id非法
            result = false;
            resultJson = "";
            userApiVo.setCode(0);
            userApiVo.setMessage("不存在此用户");
        }else {
            //填充数据
            user.setUser_id(modifyUserVo.getUser_id());
            if(modifyUserVo.getUsername() != null && modifyUserVo.getUsername() != ""){
                user.setUsername(modifyUserVo.getUsername());
            }
            if(modifyUserVo.getPassword() != null && modifyUserVo.getPassword() != ""){
                user.setPassword(modifyUserVo.getPassword());
            }
            if(modifyUserVo.getGender() != null && modifyUserVo.getGender() != ""){
                if (modifyUserVo.getGender().equals("1")){
                    //判断用户性别
                    user.setGender('1');
                } else {
                    //默认为0：女
                    user.setGender('0');
                }
            }

            if (modifyUserVo.getBirthday() != null && modifyUserVo.getBirthday() != ""){
                user.setBirthday(GetDateByStringUtils.getDate(modifyUserVo.getBirthday()));
            }
            if (modifyUserVo.getIntroduce() != null && modifyUserVo.getIntroduce() != ""){
                user.setIntroduce(modifyUserVo.getIntroduce());
            }
            if(modifyUserVo.getTelphone() != null && modifyUserVo.getTelphone() != ""){
                user.setTelphone(modifyUserVo.getTelphone());
            }

            userApiVo = userService.updateUserInformation(user);

            GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.setPrettyPrinting();        //格式化（仅用于开发阶段）
            gsonBuilder.setDateFormat("yyyy-MM-dd");
            Gson gson = gsonBuilder.create();

            resultUser = SimpleUtil.hideSensitiveInformation(userApiVo.getUser());

            resultJson = gson.toJson(resultUser);
        }
        if(userApiVo.getCode() == 0){
            resultJson = "";
        }

        return ApiFormatUtil.apiFormat(userApiVo.getCode(),userApiVo.getMessage(),resultJson);

    }


    /**
     * 修改用户头像图片
     *
     * @param file 用户新头像图片文件
     * @param request
     * @param user_id       用户ID
     * @return 修改结果
     */
    @RequestMapping(value = "/modify/head_portrail", method = {RequestMethod.POST, RequestMethod.GET},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String modifyHead_portrail(@RequestParam(value = "file", required = false) MultipartFile file,
                                      HttpServletRequest request, @RequestParam int user_id) throws Exception {

        //未进行测试

        User user = new User();
        boolean result = false;
        UserApiVo userApiVo = new UserApiVo();

        userApiVo.setMessage("");
        userApiVo.setUser(null);

        //将图片存储在服务器文件夹中，并返回文件路径
        String head_portrailResult = UploadHead_portrailUtil.uploadHead_portrail(file, request);

        //封装数据
        user.setUser_id(user_id);
        user.setHead_portrail(head_portrailResult);

        //进行数据库更新
        userApiVo = userService.updateUserInformation(user);

        return ApiFormatUtil.apiFormat(userApiVo.getCode(), userApiVo.getMessage(), "");
    }


    /**
     * 发送短信验证码
     * @param telphone
     * @return  发送结果
     * @throws Exception
     */
    @RequestMapping(value = "/sms",method = {RequestMethod.POST,RequestMethod.GET},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String sendSmsCode(String telphone, HttpServletRequest request, HttpServletResponse response) throws Exception{
        //获取session对象
        HttpSession session = request.getSession();
        int resultCode = 0;
        String result = "";

        if (telphone != "" && telphone != null){
            //接收参数正确
            if(TelphoneCheckUtil.isPhoneLegal(telphone)){
                //电话号码格式正确
                SmsVo smsVo = IndustrySMS.execute(telphone);
//                if (smsVo.getStatusCode().equals("00000")){
//                    //短信发送成功
//                    resultCode = 1;
//                    result = "短信发送成功！";
//
//                    //将验证码与电话号码存入session，并设置125秒有效期限
//                    session.setAttribute("code",smsVo.getVerificationCode());
//                    session.setAttribute("telphone",telphone);
//                    session.setMaxInactiveInterval(2*60 + 5);
//
//                } else {
//                    //短信发送失败
//                    resultCode = 0;
//                    result = "短信发送失败！" ;
//                    if (smsVo.getMiaoDiReturn() != null){
//                        result += smsVo.getMiaoDiReturn().getRespDesc();
//                    }
//                }


                //短信发送成功
                resultCode = 1;
                result = "短信发送成功！";

                //将验证码与电话号码存入session，并设置125秒有效期限
                session.setAttribute("code",smsVo.getVerificationCode());
                session.setAttribute("telphone",telphone);
                session.setMaxInactiveInterval(2*60 + 5);

//                Cookie cookieCode = new Cookie("code",smsVo.getVerificationCode());
//                Cookie cookieTelphone = new Cookie("telphone",telphone);
//                cookieCode.setMaxAge(3*60);
//                cookieTelphone.setMaxAge(3*60);
//
//                response.addCookie(cookieCode);
//                response.addCookie(cookieTelphone);
            } else {
                result  = "电话号码无效！";
                resultCode = 0;
            }
        }else {
            //未接收到参数
            result = "未接收到电话号码数据！";
            resultCode = 0;
        }

//        response.setContentType("application/json;charset=UTF-8");

        return ApiFormatUtil.apiFormat(resultCode,result,"");
    }

    /**
     * 用于找回密码
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/recover",method = {RequestMethod.POST,RequestMethod.GET},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String recoverPassword(RecoverPasswordDto recoverPasswordDto, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception{
        //声明返回
        int resultCode = 1;
        String resultMessage = "";

        //获取session
        HttpSession session = request.getSession();
        //用于存放session中的tephone和code
        String telphone = "";
        String smsCode = "";

        if (recoverPasswordDto == null){
            resultCode = 0;
            resultMessage += "未接收到参数！";
        } else {
            //参数值
            String code = recoverPasswordDto.getCode();
            String password = recoverPasswordDto.getPassword();

            if (password != null && password != ""){
                password = password.trim();
            } else {
                resultCode = 0;
                resultMessage += "新密码不能为空！";
            }

            if (resultCode != 0){
                if (session.isNew()){
                    //session不存在
                    resultCode = 0;
                    resultMessage += "验证码已过期！";
                }else {
                    //获取电话号码和验证码
                    telphone = (String) session.getAttribute("telphone");
                    smsCode = (String) session.getAttribute("code");

                    if (telphone == "" || telphone == null){
                        //未获取到手机号码
                        resultCode = 0;
                        resultMessage += "电话号码未知错误！";
                    } else {
                        telphone = telphone.trim();
                    }
                    if (telphone == "" || telphone == null){
                        resultCode = 0;
                        resultMessage += "电话号码未知错误！";
                    }

                    if (smsCode == "" || smsCode == null){
                        //未获取到短信验证码
                        resultCode = 0;
                        resultMessage += "短信验证码未知错误！";
                    } else {
                        smsCode = smsCode.trim();
                    }
                    if (smsCode == "" || smsCode == null){
                        resultCode = 0;
                        resultMessage += "短信验证码未知错误！";
                    }

                    if (code == "" || code == null){
                        //参数中未获取到短信验证码
                        resultCode = 0;
                        resultMessage += "未填写短信验证码！";
                    } else {
                        code = code.trim();
                    }
                    if (code == null || code == ""){
                        resultCode = 0;
                        resultMessage += "短信验证码为空！";
                    }

                    if (resultCode == 0){
                        //存在数据缺失
                    }else {
                        //数据获取成功
                        if (!smsCode.equals(code)){
                            //验证码不正确
                            resultCode = 0;
                            resultMessage += "验证码错误！";
                        } else {
                            UserApiVo userApiVo = userService.getUserByTelphone(telphone);
                            if (userApiVo == null){
                                //查询用户失败
                                resultCode = 0;
                                resultMessage += "用户不存在！";
                            }else {
                                //获取用户信息
                                User user = userApiVo.getUser();
                                if (user == null){
                                    //未获取到用户信息
                                    resultCode = 0;
                                    resultMessage += "获取用户信息失败！";
                                } else {
                                    //存在以telphone为电话号码的用户
                                    User temp = new User();
                                    temp.setUser_id(user.getUser_id());
                                    temp.setPassword(password);
                                    //修改用户密码
                                    UserApiVo userApiVo1 = userService.updateUserInformation(temp);
                                    if (userApiVo.getCode() == 0){
                                        //修改失败
                                        resultCode = 0;
                                        resultMessage += userApiVo.getMessage() + "找回密码失败！";
                                    } else {
                                        resultCode = 1;
                                        resultMessage = "找回密码成功！";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return ApiFormatUtil.apiFormat(resultCode,resultMessage,"");
    }


    /**
     * 获取粉丝列表
     * @param user_id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fans/get",method = {RequestMethod.POST,RequestMethod.GET},
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ArrayList<Integer> getFans(int user_id) throws Exception{
        ArrayList<Integer> result = new ArrayList<Integer>();
        try {
            result = attentionService.getFansUser(user_id);
        } catch (Exception e){
            throw e;
        }
        return result;
    }


    /**
     * 获取粉丝列表
     * @param user_id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/attention/get",method = {RequestMethod.POST,RequestMethod.GET},
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ArrayList<Integer> getAttention(int user_id) throws Exception{
        ArrayList<Integer> result = new ArrayList<Integer>();
        try {
            result = attentionService.getAttentionUser(user_id);
        } catch (Exception e){
            throw e;
        }
        return result;
    }


    /**
     * 添加关注
     * @param user_id
     * @param attention_id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/attention/add",method = {RequestMethod.POST,RequestMethod.GET},
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String insertAttention(int user_id,int attention_id) throws Exception{
        String result = "关注成功";
        try {
            attentionService.insertAttention(user_id,attention_id);
        } catch (Exception e){
            throw e;
        }
        return result;
    }

    /**
     * 取消关注
     * @param user_id
     * @param attention_id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/attention/delete",method = {RequestMethod.POST,RequestMethod.GET},
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteAttention(int user_id,int attention_id) throws Exception{
        String result = "取消关注成功";
        try {
            attentionService.deleteAttention(user_id,attention_id);
        } catch (Exception e){
            throw e;
        }
        return result;
    }


}