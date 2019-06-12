package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerThread extends Thread {

    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;

    public ServerThread(DatagramSocket datagramSocket, DatagramPacket datagramPacket) {
        this.datagramSocket = datagramSocket;
        this.datagramPacket = datagramPacket;
    }

    @Override
    public void run() {
        byte[] data = datagramPacket.getData();
        System.out.println("接收到来自"+datagramPacket.getAddress().getHostAddress()+"的消息："+new String(data, 0, data.length));
    }

    public void sendMessage(){
        InetAddress address = datagramPacket.getAddress();
        int port = datagramPacket.getPort();
        byte[] message = "欢迎!".getBytes();
        DatagramPacket packet = new DatagramPacket(message, message.length, address, port);
        try {
            datagramSocket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
