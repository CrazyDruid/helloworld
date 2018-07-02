package Test.configuration;


import Test.Config.SuperTestServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Administrator on 2018/5/21.
 */
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("Test")
@MapperScan("Test.Dao")
@EnableScheduling
@EnableCaching
public class Entry {
    public static void main(String[] args) throws Exception {
//        SpringApplication.run(Entry.class, args);
        SpringApplication springApplication = new SpringApplication(Entry.class);
        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
        SuperTestServer.setApplicationContext(configurableApplicationContext);//解决WebSocket不能注入的问题
    }

//    @Bean
//    public Queue queue(){
//        return new ActiveMQQueue("mytest.queue");
//    }

//    @Bean
//    public ActiveMQConnectionFactory connectionFactory() {
////此链接信息可放入配置文件中
//        return new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
//    }
//
//    @Bean
//    public JmsMessagingTemplate jmsMessagingTemplate(ActiveMQConnectionFactory connectionFactory){
//        System.out.println("get JmsMessagingTemplate");
//        return new JmsMessagingTemplate(connectionFactory);
//    }
}