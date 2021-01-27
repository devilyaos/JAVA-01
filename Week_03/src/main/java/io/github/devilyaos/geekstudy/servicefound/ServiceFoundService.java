package io.github.devilyaos.geekstudy.servicefound;

import io.github.devilyaos.geekstudy.exception.GatewayException;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务发现服务类
 */
public class ServiceFoundService {

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
        return urlList;
    }

    /**
     * 注册url信心
     * @param url 目标地址
     */
    public static synchronized void addUrl(String url) {
        urlList.add(url);
    }

}
