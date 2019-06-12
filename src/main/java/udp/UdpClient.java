package udp;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.io.IOException;
import java.net.*;

public class UdpClient {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("127.0.0.1");
            byte[] info = "我是客户端a".getBytes();

            DatagramPacket datagramPacket = new DatagramPacket(info, info.length, address, 8000);
            DatagramSocket clientSocket = new DatagramSocket();
            clientSocket.send(datagramPacket);

            byte[] receiveMessage = new byte[1024];
            DatagramPacket packet = new DatagramPacket(receiveMessage, receiveMessage.length);
            clientSocket.receive(packet);
            System.out.println("接收到来自"+datagramPacket.getAddress().getHostAddress()+"的消息："+new String(receiveMessage, 0, receiveMessage.length));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
