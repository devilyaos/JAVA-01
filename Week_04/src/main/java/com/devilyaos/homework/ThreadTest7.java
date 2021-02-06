package com.devilyaos.homework;

import com.devilyaos.BaseHomeWorkSupport;

import java.util.concurrent.*;

public class ThreadTest7 extends BaseHomeWorkSupport {

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        Callable<Integer> callable = () -> getCalcResult(startTime);
        ExecutorService pool = Executors.newFixedThreadPool(1);
        Future<Integer> result = pool.submit(callable);
        result.get();
    }
}
