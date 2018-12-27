package com.app.service;

import com.app.algorithm.ArticleInfo;
import com.app.entity.Dynamic;
import com.app.vo.DynamicApiVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 动态的相关service
 */
@Transactional
public interface IDynamicService {

   public DynamicApiVo getDynamicById(int dynamic_id);

   public DynamicApiVo modifyDynamicById(Dynamic dynamic);

   public DynamicApiVo deletePicById(Dynamic dynamic, String prepicfilepath);

   public DynamicApiVo insertOneDynamic(Dynamic dynamic);

   public DynamicApiVo insertPic(Dynamic dynamic);

   public DynamicApiVo findAllDynamicByUser_id(int user_id_f);

   public DynamicApiVo findDynamicByUserIdAndType(Dynamic dynamic);

   public DynamicApiVo findDynamicByType(boolean dynamic_type);

   public int findInterest_idById(int user_id_f);

   public DynamicApiVo deleteOneDynamicById(int dynamic_id);

   public List<ArticleInfo> findIdLookPersonsInterestIdByUser_id(int user_id_f);
}
