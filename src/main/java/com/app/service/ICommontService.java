package com.app.service;

/**
 *
 * 评论的相关service接口
 */

import com.app.entity.Commont;
import com.app.vo.CommontApiVo;

public interface ICommontService {
    /*1
     *@param:insertOneCommont
     *@Description：创建一个评论数据
     *@Rerurn:void
     */
    public CommontApiVo insertOneCommont(Commont commont) throws Exception;
    /*2
     *@param:findCommontById
     *@Description：通过评论的id找到一个评论数据的的全部信息
     *@Rerurn:Commont类
     */
    public CommontApiVo findCommontById(int commont_id) throws Exception;
    /*3
     *@param:findCommontByUser_id
     *@Description：通过用户id找到该用户的评论数据的全部信息
     *@Rerurn:Commont类
     */
    public CommontApiVo findCommontsByUser_id(int user_id) throws Exception;
    /*4
     *@param:findFather_CommontByCommont_id
     *@Description：寻找父评论的全部信息
     *@Rerurn:Commont类
     */
    public CommontApiVo findFather_CommontByCommont_id(int commont_id) throws Exception;
    /*5
     *@param:deleteCommontById
     *@Description：通过评论的id删除评论数据
     *@Rerurn:void
     */
    public CommontApiVo deleteCommontById(int commont_id) throws Exception;
    /*6
    *@param:findSon_Commont_idByCommont_id
    *@Description：找到子评论
    *@Rerurn:CommApiVo
    */
    public CommontApiVo findSon_Commont_idByCommont_id(int commont_id);
}
