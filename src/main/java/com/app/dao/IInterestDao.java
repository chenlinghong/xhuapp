package com.app.dao;

import com.app.entity.Interest;

/**
 *用于个性推荐的用户兴趣分类mapperDao类
 */

public interface IInterestDao {

    /**
     *在插入一个视频数据的时候同时也要创建一个对应的视频分类表
     */
    public void insertOneInterest(Interest interest);

    public Interest findInterestById(int interest_Id);

}
