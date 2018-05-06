package com.app.entity;

/**
 * @program: xhuapp
 * @description: 关注表
 * @author: Mr.Chen
 * @create: 2018-05-06 16:17
 **/
public class Attention {

    private int user_id;        //用户ID
    private int attention_user;     //被关注用户的ID

    public Attention(){

    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public int getUser_id() {
        return user_id;
    }


    public int getAttention_user() {
        return attention_user;
    }

    public void setAttention_user(int attention_user) {
        this.attention_user = attention_user;
    }
}
