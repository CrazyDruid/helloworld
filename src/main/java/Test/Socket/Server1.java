package Test.Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2018/6/5.
 */
public class Server1 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(20056);
        Socket socket = null;
        boolean flag = true;
        while (flag){
            socket = serverSocket.accept();
            System.out.println("与客户端连接成功!");
            new Thread(new ServerThread(socket)).start();
        }
        serverSocket.close();
    }
}
