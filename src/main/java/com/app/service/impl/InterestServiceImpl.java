package com.app.service.impl;

import com.app.dao.IInterestDao;
import com.app.dao.IInterestTimesDao;
import com.app.entity.Interest;
import com.app.entity.InterestTimes;
import com.app.service.IInterestService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于用户个性化推荐的相关接口实现
 */


@Component(value = "interestService")
@Scope(value = "prototype")
public class InterestServiceImpl implements IInterestService {

    @Resource
    private IInterestDao interestDao;
    @Resource
    private IInterestTimesDao iInterestTimesDao;

    public String insertOneInterest(Interest interest)throws SQLException{
        String msg = "";
        interestDao.insertOneInterest(interest);

        System.out.println(interest.getAgriculture_view());
        return msg;
    }

    public Interest findInterestById(int interest_id) {
        if(interest_id>0){
            return interestDao.findInterestById(interest_id);
        }
        return null;
    }

    public List<Map<Integer,Integer>> getMap(int user_id_f){
        InterestTimes interestTimes = iInterestTimesDao.findInterestTimesByUserId(user_id_f);
        List<Map<Integer,Integer>> mapList = new ArrayList<Map<Integer, Integer>>();

        mapList.add(new HashMap<Integer, Integer>(1,interestTimes.getCountryside_view_times()));
        mapList.add(new HashMap<Integer, Integer>(2,interestTimes.getAgriculture_view_times()));
        mapList.add(new HashMap<Integer, Integer>(3,interestTimes.getAgritainment_view_times()));
        mapList.add(new HashMap<Integer, Integer>(4,interestTimes.getOther_view_times()));
        mapList.add(new HashMap<Integer, Integer>(5,interestTimes.getAgriculture_products_times()));
        mapList.add(new HashMap<Integer, Integer>(6,interestTimes.getAgriculture_bp_times()));
        mapList.add(new HashMap<Integer, Integer>(7,interestTimes.getCreate_delifood_times()));
        mapList.add(new HashMap<Integer, Integer>(8,interestTimes.getDelifood_commont_times()));
        mapList.add(new HashMap<Integer, Integer>(9,interestTimes.getDelifood_entertainment_times()));
        mapList.add(new HashMap<Integer, Integer>(10,interestTimes.getFarm_introduce_times()));

        return mapList;
    }
}
