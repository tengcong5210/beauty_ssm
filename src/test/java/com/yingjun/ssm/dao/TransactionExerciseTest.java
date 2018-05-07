package com.yingjun.ssm.dao;

import com.yingjun.ssm.service.transaction.TransactionExercise;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author tengcongcong
 * @create 2018-05-07 18:05
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class TransactionExerciseTest {
    @Autowired
    private TransactionExercise transactionExercise;
    @Test
    public void testTransaction(){
        try {
            transactionExercise.updateUserOnRequiredNewTest();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
