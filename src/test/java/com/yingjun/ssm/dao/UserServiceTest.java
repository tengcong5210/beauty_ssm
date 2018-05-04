package com.yingjun.ssm.dao;

import com.yingjun.ssm.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author tengcongcong
 * @create 2018-05-03 16:32
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
/*@ContextConfiguration({"classpath:spring/spring-service.xml","classpath:spring/spring-dao.xml"})*/
@ContextConfiguration("classpath:spring/spring-*.xml")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testUpdateUser() {
       //1.
        // userService.updateUser();

        //2.增加异常处理的回滚
        //userService.updateUserOnExTest();

        //3.抛出检查异常，如果不做特殊处理，不会回滚，如果rollbackFor = {Exception.class},则会回滚
       /* try {
            userService.updateUserOnThrowExTest();
        }catch (Exception e){
            e.printStackTrace();
        }*/

        //4.抛出运行时异常 ,会回滚
        //userService.updateUserOnThrowRuntimeExTest();

        userService.updateUserOnRequiredNewTest();

    }
}
