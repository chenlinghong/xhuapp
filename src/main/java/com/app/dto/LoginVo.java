package com.app.dto;

/**
 * @program: xhuapp
 * @description: 用于用户登录接口Dto
 * @author: Mr.Chen
 * @create: 2018-04-17 13:51
 **/
public class LoginVo {

    private String account;     //用户账户（电话号码）
    private String password;        //用户密码

    public LoginVo(){

    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
