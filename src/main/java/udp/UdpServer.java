package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpServer {
    public static void main(String[] args) {
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(8000);
            byte[] data = new byte[1024];
            while(true){
                DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
                serverSocket.receive(datagramPacket);
                ServerThread serverThread = new ServerThread(serverSocket, datagramPacket);
                serverThread.start();
                serverThread.sendMessage();
            }
            /*String info = new String(data, 0, data.length);
            System.out.println("接收到来自"+datagramPacket.getAddress().getHostAddress()+"的消息："+info);*/
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
