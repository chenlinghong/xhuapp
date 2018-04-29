package com.app.service;

import com.app.entity.Dynamic;
import com.app.vo.DynamicApiVo;
import org.springframework.transaction.annotation.Transactional;

/*
*@Author:dxlin
*@Description：用于动态的service层
*@Date: 2018-3-23
*/
@Transactional
public interface IDynamicService {

   public DynamicApiVo getDynamicById(int dynamic_id);

   public DynamicApiVo modifyDynamicById(Dynamic dynamic);

   public DynamicApiVo deletePicById(Dynamic dynamic, String prepicfilepath);

   public DynamicApiVo insertOneDynamic(Dynamic dynamic);

   public DynamicApiVo insertPic(Dynamic dynamic);

   public DynamicApiVo findAllDynamicByUser_id(int user_id_f);

   public DynamicApiVo findDynamicByType(boolean dynamic_type);

   public DynamicApiVo deleteOneDynamicById(int dynamic_id);
}
