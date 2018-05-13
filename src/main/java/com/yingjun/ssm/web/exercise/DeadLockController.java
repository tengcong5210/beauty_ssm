package com.yingjun.ssm.web.exercise;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 死锁模拟
 */
@RestController
public class DeadLockController {

    private final Object objA=new Object();
    private final Object objB=new Object();

    @RequestMapping("/deadLockHello")
    public String deadLockHello(){
        return "死锁_hello";
    }
    @RequestMapping("deadLock")
    public void deadLock(){
        System.out.println("死锁开始");
        mockDeadLock();
        System.out.println("死锁结束");
    }

    private void mockDeadLock() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //
                synchronized (objA){
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    //
                    synchronized (objB){
                        System.out.println("线程1");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (objB){
                    synchronized (objA){
                        System.out.println("线程2");
                    }
                }
            }
        }).start();
    }


}
