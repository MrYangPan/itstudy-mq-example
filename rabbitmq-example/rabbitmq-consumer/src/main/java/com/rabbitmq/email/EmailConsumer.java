package com.rabbitmq.email;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Mr.PanYang on 2018/11/12.
 */
@Component
@RabbitListener(queues = {"new_fanout_email_queue"})
public class EmailConsumer {

    @RabbitHandler
    public void sendMsg(String msg) {
        System.out.println("Email 消费者获取生产者消息msg：" + msg);
    }

}
