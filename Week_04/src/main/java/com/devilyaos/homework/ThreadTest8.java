package com.devilyaos.homework;

import com.devilyaos.BaseHomeWorkSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest8 extends BaseHomeWorkSupport {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        long startTime = System.currentTimeMillis();
        new Thread(() -> {
            lock.lock();
            printConsole(startTime);
            lock.unlock();
        }).start();
        TimeUnit.MILLISECONDS.sleep(100);
        lock.lock();
        System.out.println("执行完成");
        lock.unlock();
    }
}
