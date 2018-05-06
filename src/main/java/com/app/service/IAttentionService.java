package com.app.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @program: xhuapp
 * @description: 用户关注service
 * @author: Mr.Chen
 * @create: 2018-05-06 16:34
 **/
@Transactional
public interface IAttentionService {

    /**
     * 添加关注
     * @param user_id
     * @param attention_user
     * @throws Exception
     */
    void insertAttention(int user_id,int attention_user) throws Exception;

    /**
     * 删除关注
     * @param user_id
     * @param attention_user
     * @throws Exception
     */
    void deleteAttention(int user_id,int attention_user) throws Exception;

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
     * 获取用户关注的所有用户ID列表
     * @param user_id
     * @return
     * @throws Exception
     */
    ArrayList<Integer> getAttentionUser(int user_id) throws Exception;

    /**
     * 获取所有粉丝ID列表
     * @param attention_user
     * @return
     * @throws Exception
     */
    ArrayList<Integer> getFansUser(int attention_user) throws Exception;
}
