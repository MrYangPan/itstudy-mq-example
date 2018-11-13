package com.activemq.configqueue;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * Created by Mr.PanYang on 2018/11/1.
 */
@Component
public class ConfigQueue {

    @Value("${my_queue}")
    private String myQueue;

    //将队列注入到spring容器中
    @Bean
    public Queue queue() {
        return new ActiveMQQueue(myQueue);
    }

    @Value("${my_topic}")
    private String myTopic;

    @Bean
    public Topic topic() {
        return new ActiveMQTopic(myTopic);
    }
}
