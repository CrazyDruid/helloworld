package Test.Socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by Administrator on 2018/6/5.
 */
public class Client1 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",20056);
        socket.setSoTimeout(10000);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));//键盘输入
        PrintStream out = new PrintStream(socket.getOutputStream());//发送服务器
        BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));//服务器接收
        boolean flag = true;
        while (flag){
            String str = input.readLine();
            out.println(str);
            if ("bye".equals(str)){
                flag = false;
            }else {
                try {
                    String eco = buf.readLine();
                    System.out.println(eco);
                }catch (Exception e){
                    System.out.println("time out");
                }
            }
        }
        input.close();
        if (socket != null){
            socket.close();
        }
    }
}
