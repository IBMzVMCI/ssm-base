package com.ssm.service;

import com.alibaba.fastjson.JSON;
import com.ssm.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xujinchao on 2015/10/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class UserServiceTest {
//    private ApplicationContext ac = null;
    @Autowired
    private UserService userService;

    @Before
    public void before() {
//        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        userService = (UserService ) ac.getBean("userService");
    }

    @Test
    public void test1() {
        User user = userService.getUserById(1);
        // System.out.println(user.getUserName());
        // logger.info("值："+user.getUserName());
        System.out.println(JSON.toJSONString(user));
    }


}
