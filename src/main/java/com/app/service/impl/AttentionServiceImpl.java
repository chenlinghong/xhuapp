package com.app.service.impl;

import com.app.dao.IAttentionDao;
import com.app.service.IAttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @program: xhuapp
 * @description:
 * @author: Mr.Chen
 * @create: 2018-05-06 16:40
 **/
@Service(value = "attentionService")
public class AttentionServiceImpl implements IAttentionService {

    @Autowired
    private IAttentionDao attentionDao;

    public AttentionServiceImpl(){

    }

    public void insertAttention(int user_id, int attention_user) throws Exception {
        try {
            attentionDao.insertAttention(user_id,attention_user);
        } catch (Exception e){
            throw e;
        }
    }

    public void deleteAttention(int user_id, int attention_user) throws Exception {
        try {
            attentionDao.deleteAttention(user_id,attention_user);
        } catch (Exception e){
            throw new Exception("未知错误");
        }
    }

    public int getAttentionNum(int user_id) throws Exception {
        int result = 0;
        try {
            result = attentionDao.getAttentionNum(user_id);
        } catch (Exception e){
            throw new Exception("未知错误");
        }
        return result;
    }

    public int getFansNum(int attention_user) throws Exception {
        int result = 0;
        try {
            result = attentionDao.getFansNum(attention_user);
        } catch (Exception e){
            throw new Exception("未知错误");
        }
        return result;
    }

    public ArrayList<Integer> getAttentionUser(int user_id) throws Exception {
        ArrayList<Integer> result = new ArrayList<Integer>();
        try {
            result = attentionDao.getAttentionUser(user_id);
        } catch (Exception e){
            throw new Exception("未知错误");
        }
        return result;
    }

    public ArrayList<Integer> getFansUser(int attention_user) throws Exception {
        ArrayList<Integer> result = new ArrayList<Integer>();
        try {
            result = attentionDao.getFansUser(attention_user);
        } catch (Exception e){
            throw new Exception("未知错误");
        }
        return result;
    }
}
