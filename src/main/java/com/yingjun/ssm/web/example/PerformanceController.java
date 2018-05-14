package com.yingjun.ssm.web.example;

import com.yingjun.ssm.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 性能相关模拟
 *
 * @author tengcongcong
 * @create 2018-05-10 9:09
 * @Version 1.0
 **/
@RestController
@RequestMapping("/performance")
public class PerformanceController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    /**
     * 测试cpu 100%
     */
    @RequestMapping("/jvmCpu")
    public void jvmCpu() {
        System.out.println("测试cpu");
        while (true) {

        }
    }

    /**
     * 测试内存打满
     */
    @RequestMapping("/jvmMem")
    public void jvmMem() {
        System.out.println("测试mem");
        List<User> users = new ArrayList<>();
        int count = 0;
        for (; ; ) {
            User user = new User();
            user.setUserId(++count);
            user.setUserName("张三");
            users.add(user);
        }
    }
}
