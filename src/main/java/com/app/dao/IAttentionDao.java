package com.app.dao;

import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * @program: xhuapp
 * @description: 用户关注Dao
 * @author: Mr.Chen
 * @create: 2018-05-06 16:19
 **/
public interface IAttentionDao {

    /**
     * 添加关注记录
     * @param user_id
     * @param attention_user
     * @throws Exception
     */
    void insertAttention(@Param("user_id")int user_id,@Param("attention_user")int attention_user) throws Exception;


    /**
     * 删除关注记录
     * @param user_id
     * @param attention_user
     * @throws Exception
     */
    void deleteAttention(@Param("user_id")int user_id,@Param("attention_user")int attention_user) throws Exception;


    /**
     * 获取关注数目
     * @param user_id
     * @return
     * @throws Exception
     */
    int getAttentionNum(int user_id) throws Exception;


    /**
     * 获取粉丝数目
     * @param attention_user
     * @return
     * @throws Exception
     */
    int getFansNum(int attention_user) throws Exception;

    /**
     * 获取关注人的ID
     * @param user_id
     * @return
     * @throws Exception
     */
    ArrayList<Integer> getAttentionUser(int user_id) throws Exception;

    /**
     * 获取所有粉丝ID
     * @param attention_user
     * @return
     * @throws Exception
     */
    ArrayList<Integer> getFansUser(int attention_user) throws Exception;

}
