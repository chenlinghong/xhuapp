package com.app.dao;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
 */

import com.app.entity.Commont;

import java.util.List;

public interface ICommontDao {
    /*1
    *@param:insertOneCommont
    *@Description：创建一个评论数据
    *@Rerurn:void
    */
    public void insertOneCommont(Commont commont);
    /*2
    *@param:findCommontById
    *@Description：通过评论的id找到一个评论数据的的全部信息
    *@Rerurn:Commont类
    */
    public Commont findCommontById(int commont_id);
    /*3
    *@param:findCommontByUser_id
    *@Description：通过用户id找到该用户的评论数据的全部信息
    *@Rerurn:Commont类
    */
    public List<Commont> findCommontsByUser_id(int user_id);
    /*4
    *@param:findFather_CommontByCommont_id
    *@Description：寻找父评论的全部信息
    *@Rerurn:Commont类
    */
    public Commont findFather_CommontByCommont_id(int commont_id);
    /*5
    *@param:findSon_Commont_idByCommont_id
    *@Description：通过评论id查找子评论字符串
    *@Rerurn:String
    */
    public List<Commont> findSon_Commont_idByCommont_id(int commont_id);
    /*6
    *@param:deleteCommontById
    *@Description：通过评论的id删除评论数据
    *@Rerurn:void
    */
    public void deleteCommontById(int commont_id);

}
