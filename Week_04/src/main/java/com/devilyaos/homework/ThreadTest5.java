package com.devilyaos.homework;

import com.devilyaos.BaseHomeWorkSupport;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ThreadTest5 extends BaseHomeWorkSupport {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        long startTime = System.currentTimeMillis();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1);
        new Thread(() -> {
            printConsole(startTime);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
