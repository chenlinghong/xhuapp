package com.app.dao;

import com.app.entity.Farm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.util.resources.en.CurrencyNames_en_MT;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring的配置文件
@ContextConfiguration({ "classpath:spring/spring-mybatis.xml" })
public class IFarmDaoTest {


    @Resource
    private IFarmDao farmDao;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findFarmByUser_id() throws Exception {
        Farm farm = farmDao.findFarmByUser_id(1);

        System.out.println("-------------------------------------------");
        System.out.println("farm_id:" + farm.getFarm_id());
        System.out.println("introduce:" + farm.getIntroduce());
        System.out.println("latitude:" + farm.getLatitude());
        System.out.println("longitude:" + farm.getLongitude());
        System.out.println("-------------------------------------------");

    }

    @Test
    public void insertFarm() throws Exception {
        Farm farm = new Farm();
        farm.setName("small");
        farm.setLatitude("123.456");
        farm.setLongitude("123.123");
        farm.setIntroduce("This is a test.");
        farm.setUser_id(2);

        farmDao.insertFarm(farm);
    }

    @Test
    public void modifyName() throws Exception {
        int user_id = 2;
        String name = "小农娱乐";
        farmDao.modifyName(name,user_id);
    }

    @Test
    public void modifyLocation_name() throws Exception {
        int user_id = 2;
        String location_name = "xhu";
        farmDao.modifyLocation_name(location_name,user_id);

    }

    @Test
    public void modifyArea() throws Exception {
        int user_id = 2;
        int area = 3;
        farmDao.modifyArea(area,user_id);
    }

    @Test
    public void modifyIntroduce() throws Exception {
        int user_id=2;
        String introduce = "This is introduce.";
        farmDao.modifyIntroduce(introduce,user_id);
    }

    @Test
    public void modifyPhoto() throws Exception {
        int user_id=2;
        String photo = "This is photo.";
        farmDao.modifyPhoto(photo,user_id);
    }

}