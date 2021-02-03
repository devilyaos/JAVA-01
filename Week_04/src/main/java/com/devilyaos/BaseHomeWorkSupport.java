package com.devilyaos;

import java.util.concurrent.TimeUnit;

public class BaseHomeWorkSupport {

    protected static void printConsole(long startTime) {
        System.out.println("异步计算结果为：" + sum());
        System.out.println("使用时间：" + (System.currentTimeMillis() - startTime) + " ms");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
