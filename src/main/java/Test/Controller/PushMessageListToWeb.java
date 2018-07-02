package Test.Controller;

import Test.Config.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sun.ye  on 2018/6/8.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/springBoot")
public class PushMessageListToWeb {
    @Autowired
    private WebSocketServer webSocketServer;

    @RequestMapping("/WebSocketServer")
    @ResponseBody
    public Map<String,Object> WebSocketServer() throws IOException {
        Map<String,Object> result =new HashMap<String,Object>();
        WebSocketServer.sendInfo("hhaah,");
        result.put("operationResult", true);
        return result;
    }

}
