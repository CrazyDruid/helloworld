package Test.MQtest;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Destination;



/**
 * Created by Administrator on 2018/5/29.
 */


@Controller
@RequestMapping(value = "/jms")
public class SpringbootJmsApplicationTests {

    @Autowired
    private Producer producer;

    @ResponseBody
    @RequestMapping(value = "/test")
    public void contextLoads() {
        Destination destination = new ActiveMQQueue("mytest.queue");
        for(int i=0; i<100; i++){
            producer.sendMessage(destination, "myname is chhliu!!!");
        }
    }
}
