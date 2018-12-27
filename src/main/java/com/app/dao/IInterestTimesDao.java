package com.app.dao;

import com.app.entity.InterestTimes;

/**
 * 用户兴趣的点击次数
 */
public interface IInterestTimesDao {
    public void insertOneInterestTimes();

    public void updataCountryside_view_times(int user_id_f);

    public void updatAagriculture_view_times(int user_id_f);

    public void updatAgritainment_view_times(int user_id_f);

    public void updatOther_view_times(int user_id_f);

    public void updatAgriculture_products_times(int user_id_f);

    public void updatAgriculture_bp_times(int user_id_f);

    public void updatCreate_delifood_times(int user_id_f);

    public void updatDelifood_commont_times(int user_id_f);

    public void updatDelifood_entertainment_times(int user_id_f);

    public void updatFarm_introduce_times(int user_id_f);

    public InterestTimes findInterestTimesByUserId(int user_id_f);

}
