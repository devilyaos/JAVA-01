package com.devilyaos.homework;

import com.devilyaos.BaseHomeWorkSupport;

import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadTest2 extends BaseHomeWorkSupport {
    public static void main(String[] args) {
        AtomicBoolean flag = new AtomicBoolean(false);
        long start = System.currentTimeMillis();
        new Thread(() -> {
            printConsole(start);
            flag.set(true);
        }).start();
        for (; !flag.get(); ) {
        }
    }
}
