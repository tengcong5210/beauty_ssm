package com.yingjun.ssm.service.transaction;

import com.yingjun.ssm.dao.UserDao;
import com.yingjun.ssm.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 事务练习
 * @author tengcongcong
 * @create 2018-05-07 18:02
 * @Version 1.0
 **/
@Service
public class TransactionExercise {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserDao userDao;

    @Transactional(propagation= Propagation.REQUIRES_NEW,rollbackFor = {Exception.class})
    public int updateUserOnRequiredTest() throws Exception{
        System.out.println("=============开始updateUserOnRequiredTest");
        User user=userDao.queryByPhone(18768128888L);
        user.setScore(19);
        userDao.updateUser(user);
        int i=1/0;
        return 0;
    }
    //@Transactional(propagation=Propagation.REQUIRES_NEW)
    @Transactional(rollbackFor = {Exception.class})
    public int updateUserOnRequiredNewTest() throws Exception {
        System.out.println("============开始updateUserOnRequiredNewTest");

        this.updateUserOnRequiredTest();

        User user=userDao.queryByPhone(18768128888L);
        user.setScore(21);
        userDao.updateUser(user);
        //int i=1/0;
        return 0;
    }
}
