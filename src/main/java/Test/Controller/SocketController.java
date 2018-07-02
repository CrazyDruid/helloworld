package Test.Controller;

import Test.Service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by Administrator on 2018/6/5.
 */

@Controller
@RequestMapping(value = "/socket")
public class SocketController {

    @Autowired
    private SocketService socketService;

    @RequestMapping(value = "/htmlOne")
    public String htmlOne(){
        return "user/htmlOne";
    }

    @RequestMapping(value = "/htmlTwo")
    public String htmlTwo(){
        return "user/htmlTwo";
    }

    @RequestMapping(value = "/getws")
    public String getws(){
        return "user/ws";
    }

    @ResponseBody
    @RequestMapping(value = "/testSocket")
    public void testSocket(String str){
        try {
            socketService.getStr(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
