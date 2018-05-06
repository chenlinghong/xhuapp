package com.app.service.impl;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
 */

import com.app.dao.ICommontDao;
import com.app.entity.Commont;
import com.app.service.ICommontService;
import com.app.vo.CommontApiVo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component(value = "commontService")
@Scope(value = "prototype")

public class CommontServiceImpl implements ICommontService {

    @Resource
    private ICommontDao commontDao;
    @Resource
    private CommontApiVo commontApiVo;


    public CommontApiVo insertOneCommont(Commont commont) throws Exception{
        commontDao.insertOneCommont(commont);
        commontApiVo.setCode(1);
        commontApiVo.setMessage("插入成功");
        return commontApiVo;
    }

    public CommontApiVo findCommontById(int commont_id) throws Exception{
        Commont commont = commontDao.findCommontById(commont_id);
        if(commont==null){
            commontApiVo.setCode(0);
            commontApiVo.setMessage("未找到评论信息");
            commontApiVo.setCommont(commont);
        }else{
            commontApiVo.setCode(1);
            commontApiVo.setMessage("成功查找");
            commontApiVo.setCommont(commont);
        }
        return commontApiVo;
    }

    public CommontApiVo findCommontsByUser_id(int user_id) throws Exception{
        List<Commont> commontList = commontDao.findCommontsByUser_id(user_id);
        commontApiVo.setCode(1);
        commontApiVo.setMessage("查找成功");
        commontApiVo.setCommontList(commontList);
        return commontApiVo;
    }

    public CommontApiVo findFather_CommontByCommont_id(int commont_id) throws Exception{
        Commont commont = commontDao.findFather_CommontByCommont_id(commont_id);
        commontApiVo.setCode(1);
        commontApiVo.setCommont(commont);
        if(commont==null){
           commontApiVo.setMessage("查找成功,但用户无父评论");
        }else {
            commontApiVo.setMessage("查找成功");
        }
        return commontApiVo;
    }

    public CommontApiVo deleteCommontById(int commont_id) throws Exception{

        commontDao.deleteCommontById(commont_id);
        commontApiVo.setCode(1);
        commontApiVo.setMessage("删除成功");
        return commontApiVo;
    }

    public CommontApiVo findSon_Commont_idByCommont_id(int commont_id) {
        List<Commont> commontList = commontDao.findSon_Commont_idByCommont_id(commont_id);
        if(commontList.size()==0){
            commontApiVo.setCode(1);
            commontApiVo.setMessage("查找成功，但此评论无子评论");
            commontApiVo.setCommontList(null);
        }else{
            commontApiVo.setCode(1);
            commontApiVo.setMessage("查找成功");
            commontApiVo.setCommontList(commontList);
        }
        return commontApiVo;
    }
}
