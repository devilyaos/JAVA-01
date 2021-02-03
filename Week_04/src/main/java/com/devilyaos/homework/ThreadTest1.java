package com.devilyaos.homework;

import com.devilyaos.BaseHomeWorkSupport;

public class ThreadTest1 extends BaseHomeWorkSupport {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            printConsole(start);
        });
        thread.start();
        thread.join();
    }
}
