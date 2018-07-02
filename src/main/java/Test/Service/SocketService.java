package Test.Service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Administrator on 2018/6/5.
 */

@Service
public class SocketService {


    public void getStr(String str) throws IOException {
        Socket socket = new Socket("localhost", 20056);
        socket.setSoTimeout(10000);
        PrintStream out = new PrintStream(socket.getOutputStream());//发送服务器
        BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));//服务器接收
        boolean flag = true;
        while (flag) {
            out.println(str);
            if ("bye".equals(str)) {
                flag = false;
            } else {
                try {
                    String eco = buf.readLine();
                    System.out.println(eco);
                } catch (Exception e) {
                    System.out.println("time out");
                }
            }
        }
        if (socket != null) {
            socket.close();
        }
    }
}
