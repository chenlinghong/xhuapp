package dao;

import com.app.dao.IUserDao;
import com.app.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: busis
 * @description:
 * @author: Mr.Chen
 * @create: 2018-03-12 14:25
 **/
public class UserDaoTest {

    private ApplicationContext applicationContext;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("spring/spring-mybatis.xml");
    }

    @Test
    public void testFindUserById() throws Exception {
        System.out.println(applicationContext);

        IUserDao userDao = (IUserDao) applicationContext.getBean("userMapper");
        User user = userDao.findUserByUser_id(1);
        System.out.println("id: " + user.getUser_id());
        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());
        System.out.println("birthday: " + user.getBirthday());
        System.out.println("head_portrail: " + user.getHead_portrail());
        System.out.println("gender: " + user.getGender());
        System.out.println("introduce: " + user.getIntroduce());
        System.out.println("telphone: " + user.getTelphone());

    }


}
