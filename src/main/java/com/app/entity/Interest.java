package com.app.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 视频建立时对应的后台个性算法的兴趣分类表
 */

@Component(value = "interest")
@Scope(value = "prototype")
public class Interest {
    private int interest_id;
    private int agriculture_view;
    private int agriculture_products;
    private int agriculture_bp;
    private int other_view;
    private int create_delifood;
    private int delifood_commont;
    private int farm_introduce;

    private int countryside_view;
    private int agritainment_view;
    private int delifood_entertainment;

    public Interest(){}

    public int getAgritainment_view() {
        return agritainment_view;
    }

    public void setAgritainment_view(int agritainment_view) {
        this.agritainment_view = agritainment_view;
    }

    public int getDelifood_entertainment() {
        return delifood_entertainment;
    }

    public void setDelifood_entertainment(int delifood_entertainment) {
        this.delifood_entertainment = delifood_entertainment;
    }

    public int getCountryside_view() {
        return countryside_view;
    }

    public void setCountryside_view(int countryside_view) {
        this.countryside_view = countryside_view;
    }

    public int getInterest_id() {
        return interest_id;
    }

    public void setInterest_id(int interest_id) {
        this.interest_id = interest_id;
    }

    public int getAgriculture_view() {
        return agriculture_view;
    }

    public void setAgriculture_view(int agriculture_view) {
        this.agriculture_view = agriculture_view;
    }

    public int getAgriculture_products() {
        return agriculture_products;
    }

    public void setAgriculture_products(int agriculture_products) {
        this.agriculture_products = agriculture_products;
    }

    public int getAgriculture_bp() {
        return agriculture_bp;
    }

    public void setAgriculture_bp(int agriculture_bp) {
        this.agriculture_bp = agriculture_bp;
    }

    public int getOther_view() {
        return other_view;
    }

    public void setOther_view(int other_view) {
        this.other_view = other_view;
    }

    public int getCreate_delifood() {
        return create_delifood;
    }

    public void setCreate_delifood(int create_delifood) {
        this.create_delifood = create_delifood;
    }

    public int getDelifood_commont() {
        return delifood_commont;
    }

    public void setDelifood_commont(int delifood_commont) {
        this.delifood_commont = delifood_commont;
    }

    public int getFarm_introduce() {
        return farm_introduce;
    }

    public void setFarm_introduce(int farm_introduce) {
        this.farm_introduce = farm_introduce;
    }
}
