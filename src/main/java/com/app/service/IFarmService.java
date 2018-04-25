package com.app.service;

import com.app.dto.FarmDto;
import com.app.entity.Farm;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: xhuapp
 * @description: 农场相关业务逻辑service类
 * @author: Mr.Chen
 * @create: 2018-04-23 21:04
 **/
@Transactional
public interface IFarmService {

    /**
     * 通过用户ID获得农场信息
     * @param user_id
     * @return
     * @throws Exception
     */
    public FarmDto findFarmByUser_id(int user_id) throws Exception;


    /**
     * 新增农场
     * @param farm
     * @return
     * @throws Exception
     */
    public FarmDto insertFarm(Farm farm) throws Exception;


    /**
     * 修改农场基本信息
     * @param farm
     *      name : 农场名称
     *      location_name : 农场位置名称
     *      area : 农场面积
     *      introduce : 农场介绍
     * @return
     * @throws Exception
     */
    public FarmDto modifyFarmInfo(Farm farm) throws Exception;


    /**
     * 修改图片路径
     * @param photo
     * @param user_id
     * @return
     * @throws Exception
     */
    public FarmDto modifyPhoto(String photo,int user_id) throws Exception;



}
