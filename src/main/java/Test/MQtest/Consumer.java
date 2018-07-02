package Test.MQtest;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/5/29.
 */

@Component
public class Consumer {

//    @JmsListener(destination = "mytest.queue")
    public void receiveQueue(String text) {
        System.out.println("Consumer收到的报文为:"+text);
     }
}
