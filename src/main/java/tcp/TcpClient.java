package tcp;

import java.io.*;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        OutputStream outputStream = null;
        PrintWriter printWriter = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            //1.建立客户端socket
            socket= new Socket("127.0.0.1", 8000);
            //2.获取输出流
            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
            printWriter.write("我是客户端b");
            printWriter.flush();
            socket.shutdownOutput();

            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String info = null;
            while ((info = bufferedReader.readLine()) != null){
                System.out.println("客户端收到的消息："+info);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bufferedReader != null) bufferedReader.close();
            if(inputStream != null) inputStream.close();
            if(printWriter != null) printWriter.close();
            if(outputStream != null) outputStream.close();
            if(socket != null) socket.close();
        }
    }
}
