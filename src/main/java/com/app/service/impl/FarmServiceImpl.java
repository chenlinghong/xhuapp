package com.app.service.impl;

import com.app.dao.IFarmDao;
import com.app.dao.IUserDao;
import com.app.dto.FarmDto;
import com.app.entity.Farm;
import com.app.entity.User;
import com.app.service.IFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: xhuapp
 * @description:
 * @author: Mr.Chen
 * @create: 2018-04-23 21:18
 **/

@Service(value = "farmService")
public class FarmServiceImpl implements IFarmService{

    @Autowired
    private IFarmDao farmDao;

    @Autowired
    private IUserDao userDao;

    public FarmDto findFarmByUser_id(int user_id) throws Exception {
        FarmDto farmDto = new FarmDto();
        farmDto.setCode(1);
        farmDto.setMessage("");

        Farm farm = farmDao.findFarmByUser_id(user_id);

        if (farm == null){
            farmDto.setCode(0);
            farmDto.setMessage(farmDto.getMessage() + "该用户未拥有农场！");
            farmDto.setFarm(null);
        } else {
            farmDto.setCode(1);
            farmDto.setMessage("查询成功！");
            farmDto.setFarm(farm);
        }

        return farmDto;
    }

    public FarmDto insertFarm(Farm farm) throws Exception {
        FarmDto farmDto = new FarmDto();
        farmDto.setCode(1);
        farmDto.setMessage("");
        farmDto.setFarm(null);

        if (farm == null){
            farmDto.setCode(0);
            farmDto.setMessage(farmDto.getMessage() + "未接收到参数！");
            farmDto.setFarm(null);
        } else {
            User user = userDao.findUserByUser_id(farm.getUser_id());
            if (user == null){
                farmDto.setCode(0);
                farmDto.setMessage(farmDto.getMessage() + "此用户不存在！");
                farmDto.setFarm(null);
            } else {
                if (farm.getName() != "" && farm.getName() != null){
                    if (farm.getName().length() > 20){
                        farmDto.setCode(0);
                        farmDto.setMessage(farmDto.getMessage() + "农场名称长度过长！");
                        farmDto.setFarm(null);
                    }
                }
                if (farm.getLongitude() == "" || farm.getLongitude() == null){
                    farmDto.setCode(0);
                    farmDto.setMessage(farmDto.getMessage() + "经度为空！");
                } else if (farm.getLongitude().length() > 30){
                    farmDto.setCode(0);
                    farmDto.setMessage(farmDto.getMessage() + "经度位数过长！");
                } else {
                    //TODO
                }

                if (farm.getLatitude() == "" || farm.getLatitude() == null){
                    farmDto.setCode(0);
                    farmDto.setMessage(farmDto.getMessage() + "纬度为空！");
                } else if (farm.getLatitude().length() > 30){
                    farmDto.setCode(0);
                    farmDto.setMessage(farmDto.getMessage() + "纬度位数过长！");
                } else {
                    //TODO

                }

                if (farm.getLocation_name() != "" && farm.getLocation_name() != null){
                    if (farm.getLocation_name().length() > 30){
                        farmDto.setCode(0);
                        farmDto.setMessage(farmDto.getMessage() + "农场位置名称长度过长！");
                        farmDto.setFarm(null);
                    }
                }

                if (farm.getIntroduce() != "" && farm.getIntroduce() != null){
                    if (farm.getIntroduce().length() > 300){
                        farmDto.setCode(0);
                        farmDto.setMessage(farmDto.getMessage() + "农场介绍长度过长！");
                        farmDto.setFarm(null);
                    }
                }

                if (farm.getPhoto() != "" && farm.getPhoto() != null){
                    if (farm.getPhoto().length() > 20){
                        farmDto.setCode(0);
                        farmDto.setMessage(farmDto.getMessage() + "农场图片过多！");
                        farmDto.setFarm(null);
                    }
                }

                if (farmDto.getCode() == 0){
                    //存在非法数据
                    //TODO

                } else {
                    farmDao.insertFarm(farm);
                    farmDto.setCode(1);
                    farmDto.setMessage("新建农场成功！");
                    farmDto.setFarm(farm);
                }
            }
        }

        return farmDto;
    }

    public FarmDto modifyFarmInfo(Farm farm) throws Exception {
        FarmDto farmDto = new FarmDto();
        farmDto.setCode(1);
        farmDto.setMessage("");
        farmDto.setFarm(null);

        if (farm == null){
            farmDto.setCode(0);
            farmDto.setMessage(farmDto.getMessage() + "未接收到参数！");
        } else {
            Farm oldFarm = farmDao.findFarmByUser_id(farm.getUser_id());
            Farm modifyFarm = new Farm();
            if (oldFarm == null){
                farmDto.setCode(0);
                farmDto.setMessage(farmDto.getMessage() + "不存在此农场！");
            } else {
                if (farm.getName() != null && farm.getName() != ""){
                    if (!farm.getName().equals(oldFarm.getName())){
                        if (farm.getName().length() < 20){
                            modifyFarm.setName(farm.getName());
                        } else {
                            farmDto.setCode(0);
                            farmDto.setMessage(farmDto.getMessage() + "农场名称过长！");
                        }
                    }
                }
//                if (farm.getLongitude() != null && farm.getLongitude() != "" &&
//                        !farm.getLongitude().equals(oldFarm.getLongitude())){
//                    if (farm.getLongitude().length() < 30){
//                        modifyFarm.setLongitude(farm.getLongitude());
//                    } else {
//                        farmDto.setCode(0);
//                        farmDto.setMessage(farmDto.getMessage() + "经度位数过长！");
//                    }
//                }
//                if (farm.getLatitude() != null && farm.getLatitude() != "" &&
//                        !farm.getLatitude().equals(oldFarm.getLatitude())){
//                    if (farm.getLatitude().length() < 30){
//                        modifyFarm.setLatitude(farm.getLatitude());
//                    } else{
//                        farmDto.setCode(0);
//                        farmDto.setMessage(farmDto.getMessage() + "纬度位数过长！");
//                    }
//                }

                if ((farm.getLongitude() != null && farm.getLongitude() != "" &&
                        !farm.getLongitude().equals(oldFarm.getLongitude())) ||
                        (farm.getLatitude() != null && farm.getLatitude() != "" &&
                        !farm.getLatitude().equals(oldFarm.getLatitude()))){
                    farmDto.setCode(0);
                    farmDto.setMessage(farmDto.getMessage() + "不可修改农场地址！");
                }

                if (farm.getLocation_name() != null && farm.getLocation_name() != "" &&
                        !farm.getLocation_name().equals(oldFarm.getLocation_name())){
                    if (farm.getLocation_name().length() < 50){
                        modifyFarm.setLocation_name(farm.getLocation_name());
                    } else {
                        farmDto.setCode(0);
                        farmDto.setMessage(farmDto.getMessage() + "位置名称过长！");
                    }
                }
                if (farm.getArea() != 0 && farm.getArea() != oldFarm.getUser_id()){
                    modifyFarm.setUser_id(farm.getUser_id());
                } else {
                    farm.setArea(0);
                }

                if (farm.getIntroduce() != null && farm.getIntroduce() != "" &&
                        !farm.getIntroduce().equals(oldFarm.getIntroduce())){
                    if (farm.getIntroduce().length() < 300){
                        modifyFarm.setIntroduce(farm.getIntroduce());
                    } else {
                        farmDto.setCode(0);
                        farmDto.setMessage(farmDto.getMessage() + "农场介绍过长！");
                    }
                }

                modifyFarm.setUser_id(farm.getUser_id());

                if (farmDto.getCode() == 0){
                    //存在参数不合法
                    //TODO
                }else {
                    if (modifyFarm.getName() != null && modifyFarm.getName() != ""){
                        farmDao.modifyName(modifyFarm.getName(),modifyFarm.getUser_id());
                    }
                    if (modifyFarm.getLocation_name() != null && modifyFarm.getLocation_name() != ""){
                        farmDao.modifyLocation_name(modifyFarm.getLocation_name(),modifyFarm.getUser_id());
                    }
                    if (modifyFarm.getArea() != 0){
                        farmDao.modifyArea(modifyFarm.getArea(),modifyFarm.getUser_id());
                    }
                    if (modifyFarm.getIntroduce() != null && modifyFarm.getIntroduce() != ""){
                        farmDao.modifyIntroduce(modifyFarm.getIntroduce(),modifyFarm.getUser_id());
                    }
                }
            }
        }

        return farmDto;
    }
}
