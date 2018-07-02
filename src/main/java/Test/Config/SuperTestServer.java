package Test.Config;

import Test.Entity.User;
import Test.Service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Administrator on 2018/6/11.
 */
//
@ServerEndpoint(value = "/superTest",configurator = WebSocketConfig.class)
@Component
public class SuperTestServer {

    private static int count = 0;

    private Session session;

    private static CopyOnWriteArraySet<SuperTestServer> cowas = new CopyOnWriteArraySet<SuperTestServer>();

    private static ApplicationContext applicationContext;

    private UserService userService;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SuperTestServer.applicationContext = applicationContext;
    }


    @OnOpen
    public void onOpen(Session session,EndpointConfig config){
        this.session = session;
        HttpSession hs = (HttpSession)config.getUserProperties().get("sessionid");
        Map<String, List<String>> map = (Map<String, List<String>>)config.getUserProperties().get("map");
        String id = map.get("id")==null?"":((List<String>)map.get("id")).get(0);
        System.out.println(map);
        System.out.println(id);
        System.out.println(hs);
        userService = applicationContext.getBean(UserService.class);
        User user = userService.getUser(id);
        System.out.println(user);

        cowas.add(this);
        addCount();
        System.out.println("有新连接加入！当前在线人数为" + getCount());
        try {
            for (SuperTestServer sts:cowas){
                sts.send("欢迎" + user.getName() + "加入到聊天室");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(){
        cowas.remove(this);
        removeCount();
        System.out.println("有一连接关闭！当前在线人数为" + getCount());
    }

    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("错误");
//        error.printStackTrace();
    }

    @OnMessage
    public void onMessage(String message,Session session){
        System.out.println("来自客户端的消息:" + message);
        for (SuperTestServer sts:cowas){
            try {
                sts.send(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getCount(){
        return count;
    }

    private static synchronized void addCount(){
       SuperTestServer.count++;
    }

    public static synchronized void removeCount(){
        SuperTestServer.count--;
    }


}
