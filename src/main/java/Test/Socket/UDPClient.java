package Test.Socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Administrator on 2018/6/5.
 */
public class UDPClient {

    private static final int TIMEOUT = 5000;
    private static final int MAXNUM = 5;

    public static void main(String[] args) throws IOException {
        String str_send = "Hello UDPserver";
        byte[] bytes = new byte[1024];
        DatagramSocket ds = new DatagramSocket(9000);
        InetAddress inetAddress = InetAddress.getLocalHost();
        DatagramPacket dp_send = new DatagramPacket(str_send.getBytes(),str_send.length(),inetAddress,3000);
        DatagramPacket dp_receive = new DatagramPacket(bytes,1024);
        ds.setSoTimeout(TIMEOUT);
        int tries = 0;
        boolean flag = false;
        while (!flag && tries < MAXNUM){
            ds.send(dp_send);
            try {
                ds.receive(dp_receive);
                if (!dp_receive.getAddress().equals(inetAddress)){
                    throw new IOException("地址错误!");
                }
                flag = true;
            }catch (Exception e){
                tries += 1;
                System.out.println("连接超时,剩余" + (MAXNUM - tries) + "次..." );
            }
        }

        if (flag){
            System.out.println("client received data from server：");
            String str_receive = new String(dp_receive.getData(),0,dp_receive.getLength()) +
                    " from " + dp_receive.getAddress().getHostAddress() + ":" + dp_receive.getPort();
            System.out.println(str_receive);
            dp_receive.setLength(1024);
        }else {
            System.out.println("没有数据");
        }
        ds.close();
    }
}
