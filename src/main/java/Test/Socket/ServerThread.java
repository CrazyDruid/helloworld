package Test.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Administrator on 2018/6/5.
 */
public class ServerThread implements Runnable {

    private Socket sockets;

    public ServerThread(Socket sockets) {
        this.sockets = sockets;
    }

    @Override
    public void run() {
        try {
            BufferedReader write = new BufferedReader(new InputStreamReader(System.in));
            PrintStream out = new PrintStream(sockets.getOutputStream());//发送客户端
            BufferedReader in = new BufferedReader(new InputStreamReader(sockets.getInputStream()));//接受数据
            boolean flag = true;
            while (flag){
                String str = in.readLine();
                System.out.println("客户端说:" + str);
                if (str == null || "".equals(str)){
                    flag = false;
                }else {
                    if ("bye".equals(str)){
                        flag = false;
                    }else {
                        out.println("服务器说:" + write.readLine());
                    }
                }
            }
            out.close();
            sockets.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
