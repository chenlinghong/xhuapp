package com.app.dao;

import com.app.entity.Dynamic;

import java.util.List;

/**
 * dynamic相关操作
 */
public interface IDynamicDao {

    /**
     * 通过动态ID查询动态基本信息
     * @param dynamic_id
     * @return 动态信息
     * @throws Exception
     */
    public Dynamic findDynamicById(int dynamic_id);


    /*
     *@param:dynamic_id int
     *@Description:找到是否有这个动态,内部用
     *@Rerurn:int
     */
    public Integer findisDynamic(int dynamic_id);


    /*
     *@param:user_id_f
     *@Description：找到用户的全部动态
     *@Rerurn:List<Dynamic>
     */
    public List<Dynamic> findAllDynamicByUserId(int user_id_f);


    /*通过ID修改动态
    *@param:dynamic
    *@Description：通过ID修改动态
    *@Rerurn:void
    */
    public void modifyDynamicById(Dynamic dynamic);


    /*
    *@param:dynamic对象
    *@Description：增加一个动态对象
    *@Rerurn:void
    */
    public void insertOneDynamic(Dynamic dynamic);


    /*
    *@param:dynamic_id int
    *@Description：内部使用，通过ID找到图片的绝对地址
    *@Rerurn:String
    */
    public String findPicturePathByIDd(int dynamic_id);


    /*
    *@param:filepath String
    *@Description：存储图片专用
    *@Rerurn:return String
    */
    public void modifyPicById(Dynamic dynamic);

}
