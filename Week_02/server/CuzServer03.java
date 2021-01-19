package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 同步非阻塞线程池版
 */
public class CuzServer03 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8803);
        // 此处压测性能不高, 与ppt命令结论差异较大, 暂怀疑与机器有效核数有关
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10000));
        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                pool.execute(() -> service(socket));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
