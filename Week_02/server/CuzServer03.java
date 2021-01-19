package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 同步非阻塞线程池版
 */
public class CuzServer03 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8803);
        ExecutorService pool = Executors.newFixedThreadPool(20);
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
