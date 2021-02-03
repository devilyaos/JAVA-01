package com.devilyaos.homework;

import com.devilyaos.BaseHomeWorkSupport;

import java.util.concurrent.CountDownLatch;

public class ThreadTest4 extends BaseHomeWorkSupport {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(() -> {
            printConsole(startTime);
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();
    }
}
