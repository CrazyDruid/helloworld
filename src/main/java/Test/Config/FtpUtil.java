package Test.Config;

/**
 * Created by sun.ye  on 2018/6/15.
 */
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

public class FtpUtil {

    FtpClient ftpClient;

    /***
     * 连接ftp
     * @param url  必须是  192.168.8.1  否则提示异常
     * @param port
     * @param username
     * @param password
     * @return
     */
    public static FtpClient connectFTP(String url, int port, String username, String password) {
        //创建ftp
        FtpClient ftp = null;
        try {
            //创建地址
            SocketAddress addr = new InetSocketAddress(url, port);
            //连接
            ftp = FtpClient.create();
            ftp.connect(addr);
            //登陆
            ftp.login(username, password.toCharArray());
            ftp.setBinaryType();
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftp;
    }

    public static List<String> download(String ftpFile, FtpClient ftp) {
        List<String> list = new ArrayList<String>();
        String str = "";
        InputStream is = null;
        BufferedReader br = null;
        try {
            // 获取ftp上的文件
            is = ftp.getFileStream(ftpFile);
            //转为字节流
            br = new BufferedReader(new InputStreamReader(is));
            while((str=br.readLine())!=null){
                list.add(str);
            }
            br.close();
        }catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        FtpClient ftp = connectFTP("192.168.51.200",21,"ftp-java","java");
        List<String> list = download("test/261165900000003-20171110checkfile.txt",ftp);
        for(int i=0;i<list.size();i++){
            System.out.println("list "+ i + " :"+list.get(i));
        }
        try {
            ftp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
