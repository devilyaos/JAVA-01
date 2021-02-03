package com.devilyaos.homework;

import com.devilyaos.BaseHomeWorkSupport;

import java.util.concurrent.Semaphore;

public class ThreadTest6 extends BaseHomeWorkSupport {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        long startTime = System.currentTimeMillis();
        new Thread(() -> {
            printConsole(startTime);
            semaphore.release();
        }).start();
        semaphore.acquire();
    }
}
