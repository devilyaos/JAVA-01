package io.github.devilyaos.geekstudy.servicefound;

import io.github.devilyaos.geekstudy.exception.GatewayException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 服务发现服务类
 */
public class ServiceFoundService {

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private static List<String> urlList = new ArrayList<>();

    /**
     * 使用静态方法提供接口, 无需初始化
     * @throws IllegalAccessException 非法权限
     */
    private ServiceFoundService() throws IllegalAccessException {
        throw new GatewayException("ServiceFoundService禁止无参初始化");
    }

    /**
     * 获取当前连接列表
     * @return 所有的链接列表
     */
    public static List<String> getUrlList() {
        try {
            lock.readLock().tryLock();
            return urlList;
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * 注册url信心
     * @param url 目标地址
     */
    public static synchronized void addUrl(String url) {
        try {
            lock.writeLock().tryLock();
            urlList.add(url);
        } finally {
            lock.writeLock().unlock();
        }
    }

}
