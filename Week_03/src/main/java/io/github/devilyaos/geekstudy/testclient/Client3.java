package io.github.devilyaos.geekstudy.testclient;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 第3个客户端
 */
public class Client3 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8803);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10000));
        System.out.println("启动...");
        // 启动后注册服务
        OkHttpClient httpClient = new OkHttpClient();
        String url = "http://127.0.0.1:8888/reportRoute?target=http://127.0.0.1:8803";
        Request request = new Request.Builder().url(url).get().addHeader("study-group", "java0101-req").build();
        Response response = httpClient.newCall(request).execute();
        String responseMsg = response.body().string();
        if ("SUCCESS".equalsIgnoreCase(responseMsg)) {
            System.out.println("注册服务成功!!");
            // 开始接收服务
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("=========================");
                    System.out.println("收到一次请求..");
                    pool.execute(() -> service(socket));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("注册服务失败...");
        }
    }

    private static void service(Socket socket) {
        System.out.println("开始处理请求");
        try {
            String content = "test test test";
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println("Content-Length: " + content.getBytes().length);
            printWriter.println();
            printWriter.write(content);
            printWriter.close();
            socket.close();
            System.out.println("返回请求...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
