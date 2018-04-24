package com.app.dao;

import com.app.entity.Farm;
import org.apache.ibatis.annotations.Param;

/**
 * @program: xhuapp
 * @description: 农场基本信息相关DAO
 * @author: Mr.Chen
 * @create: 2018-04-23 19:28
 **/
public interface IFarmDao {


    /**
     * 通过用户ID获得农场信息
     * @param user_id
     * @return
     * @throws Exception
     */
    public Farm findFarmByUser_id(int user_id) throws Exception;

    /**
     * 添加农场
     * @param farm
     * @throws Exception
     */
    public void insertFarm(Farm farm) throws Exception;


    /**
     * 修改农场名称
     * @param user_id
     * @param name
     * @throws Exception
     */
    public void modifyName(@Param("name") String name,@Param("user_id") int user_id) throws Exception;


    /**
     * 修改农场位置名称
     * @param location_name
     * @param user_id
     * @throws Exception
     */
    public void modifyLocation_name(@Param("location_name") String location_name,@Param("user_id") int user_id)
            throws Exception;

    /**
     * 修改农场面积
     * @param area
     * @param user_id
     * @throws Exception
     */
    public void modifyArea(@Param("area") int area,@Param("user_id") int user_id) throws Exception;


    /**
     * 修改农场介绍
     * @param introduce
     * @param user_id
     * @throws Exception
     */
    public void modifyIntroduce(@Param("introduce") String introduce,@Param("user_id") int user_id) throws Exception;


    /**
     * 修改农场图片
     * @param photo
     * @param user_id
     * @throws Exception
     */
    public void modifyPhoto(@Param("photo") String photo,@Param("user_id") int user_id) throws Exception;


}
