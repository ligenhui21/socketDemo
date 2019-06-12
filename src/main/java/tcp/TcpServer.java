package tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        ServerSocket serverSocket = null;
        try {
            //1.创建serverSocket并监听指定端口
            serverSocket = new ServerSocket(8000);
            System.out.println("服务器端已启动。。。");
            int count = 0;
            while(true){
                //2.等待客户端连接
                socket = serverSocket.accept();
                Thread thread = new ServerThread(socket);
                thread.start();
                System.out.println("连接的客户端数量："+ ++count);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
