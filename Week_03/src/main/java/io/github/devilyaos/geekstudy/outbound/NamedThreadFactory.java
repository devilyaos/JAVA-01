package io.github.devilyaos.geekstudy.outbound;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义的线程工厂
 */
public class NamedThreadFactory implements ThreadFactory {
    /**
     * 线程组
     */
    private final ThreadGroup group;
    /**
     * 当前线程总计数
     */
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    /**
     * 名称前缀
     */
    private final String namePrefix;
    /**
     * 是否设置守护线程: true - 是 / false - 否
     */
    private final boolean daemon;

    /**
     * 构造方法
     *
     * @param namePrefix 名称前缀
     * @param daemon     是否守护线程
     */
    public NamedThreadFactory(String namePrefix, boolean daemon) {
        this.namePrefix = namePrefix;
        this.daemon = daemon;
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
    }

    /**
     * 构造方法
     *
     * @param namePrefix 名称前缀
     */
    public NamedThreadFactory(String namePrefix) {
        this(namePrefix, false);
    }


    public Thread newThread(@NotNull Runnable r) {
        Thread t = new Thread(group, r, namePrefix + "-thread-" + threadNumber.getAndIncrement(), 0);
        t.setDaemon(daemon);
        return t;
    }
}
