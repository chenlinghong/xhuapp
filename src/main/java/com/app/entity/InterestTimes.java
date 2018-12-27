package com.app.entity;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
 */

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "interestTiems")
@Scope(value = "prototype")
public class InterestTimes {

    private int interest_click_id;
    private int user_id_f;

    private int countryside_view_times;
    private int agriculture_view_times;
    private int agritainment_view_times;
    private int other_view_times;
    private int agriculture_products_times;
    private int agriculture_bp_times;
    private int create_delifood_times;
    private int delifood_commont_times;
    private int delifood_entertainment_times;
    private int farm_introduce_times;

    public InterestTimes(){}

    public int getInterest_click_id() {
        return interest_click_id;
    }

    public void setInterest_click_id(int interest_click_id) {
        this.interest_click_id = interest_click_id;
    }

    public int getUser_id_f() {
        return user_id_f;
    }

    public void setUser_id_f(int user_id_f) {
        this.user_id_f = user_id_f;
    }

    public int getCountryside_view_times() {
        return countryside_view_times;
    }

    public void setCountryside_view_times(int countryside_view_times) {
        this.countryside_view_times = countryside_view_times;
    }

    public int getAgriculture_view_times() {
        return agriculture_view_times;
    }

    public void setAgriculture_view_times(int agriculture_view_times) {
        this.agriculture_view_times = agriculture_view_times;
    }

    public int getAgritainment_view_times() {
        return agritainment_view_times;
    }

    public void setAgritainment_view_times(int agritainment_view_times) {
        this.agritainment_view_times = agritainment_view_times;
    }

    public int getOther_view_times() {
        return other_view_times;
    }

    public void setOther_view_times(int other_view_times) {
        this.other_view_times = other_view_times;
    }

    public int getAgriculture_products_times() {
        return agriculture_products_times;
    }

    public void setAgriculture_products_times(int agriculture_products_times) {
        this.agriculture_products_times = agriculture_products_times;
    }

    public int getAgriculture_bp_times() {
        return agriculture_bp_times;
    }

    public void setAgriculture_bp_times(int agriculture_bp_times) {
        this.agriculture_bp_times = agriculture_bp_times;
    }

    public int getCreate_delifood_times() {
        return create_delifood_times;
    }

    public void setCreate_delifood_times(int create_delifood_times) {
        this.create_delifood_times = create_delifood_times;
    }

    public int getDelifood_commont_times() {
        return delifood_commont_times;
    }

    public void setDelifood_commont_times(int delifood_commont_times) {
        this.delifood_commont_times = delifood_commont_times;
    }

    public int getDelifood_entertainment_times() {
        return delifood_entertainment_times;
    }

    public void setDelifood_entertainment_times(int delifood_entertainment_times) {
        this.delifood_entertainment_times = delifood_entertainment_times;
    }

    public int getFarm_introduce_times() {
        return farm_introduce_times;
    }

    public void setFarm_introduce_times(int farm_introduce_times) {
        this.farm_introduce_times = farm_introduce_times;
    }
}
