package Test.Controller;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Administrator on 2018/6/7.
 */
@ServerEndpoint("/websocket/demo")
public class TestSocketController {

    @OnMessage      //这里是方法注解，在接收到客户端的消息时触发
    public String echo(String clientMessage){
        return "收到来自客户端的消息：" + clientMessage;
    }

}
