package Test.Socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2018/6/5.
 */
public class Test {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress ia = InetAddress.getLocalHost();
        System.out.println(ia.getHostName()+"..."+ia.getHostAddress());
    }
}
