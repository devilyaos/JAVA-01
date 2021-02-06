package com.devilyaos.homework;

import com.devilyaos.BaseHomeWorkSupport;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest3 extends BaseHomeWorkSupport {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        long startTime = System.currentTimeMillis();
        pool.execute(() -> {
            printConsole(startTime);
        });
        pool.shutdown();
    }
}
