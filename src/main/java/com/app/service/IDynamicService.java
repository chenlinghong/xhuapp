package com.app.service;

import com.app.entity.Dynamic;
import com.app.vo.DynamicApiVo;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: xhuapp
 * @description: Dynamic实体类相关的service层接口
 * @author: Mr.Chen
 * @create: 2018-04-02 15:45
 **/
@Transactional
public interface IDynamicService {

    public DynamicApiVo getDynamicById(int dynamic_id);

    public DynamicApiVo modifyDynamicById(Dynamic dynamic);

    public DynamicApiVo insertOneDynamic(Dynamic dynamic);

    public DynamicApiVo modifyPicById(Dynamic dynamic);

    public DynamicApiVo findAllDynamicByUser_id(int user_id_f);


}
