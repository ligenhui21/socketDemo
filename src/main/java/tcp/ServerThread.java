package tcp;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {

    Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        InputStream inputStream = null;
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;
        OutputStream outputStream = null;
        PrintWriter printWriter = null;
        try {
            inputStream = socket.getInputStream();
            reader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(reader);
            String info = null;
            while((info = bufferedReader.readLine()) != null){
                System.out.println("服务器端接收到的消息：" + info);
            }
            //4.关闭输入流
            socket.shutdownInput();

            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
            printWriter.write("我是服务器端，已经收到消息。。。");
            printWriter.flush();
            socket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(printWriter != null) printWriter.close();
                if(outputStream != null) outputStream.close();
                if(bufferedReader != null) bufferedReader.close();
                if(reader != null) reader.close();
                if(inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
