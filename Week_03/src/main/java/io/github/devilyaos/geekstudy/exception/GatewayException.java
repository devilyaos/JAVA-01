package io.github.devilyaos.geekstudy.exception;

/**
 * 自定义异常类
 */
public class GatewayException extends RuntimeException {

    /**
     * 使用带信息的构造函数初始化
     * @param message 错误信息
     */
    public GatewayException(String message) {
        super(message);
    }
}
