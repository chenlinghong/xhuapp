package com.app.service;

import com.app.entity.Interest;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *用于个性化推荐的兴趣服务层相关接口
 */
public interface IInterestService {

    public String insertOneInterest(Interest interest) throws SQLException;

    public Interest findInterestById(int interest_id);

    List<Map<Integer,Integer>> getMap(int user_id_f);
}
