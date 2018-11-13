package com.rabbitmq.sms;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Mr.PanYang on 2018/11/12.
 */
@Component
@RabbitListener(queues = {"new_fanout_sms_queue"})
public class SmsController {

    @RabbitHandler
    public void sendMsg(String msg) {
        System.out.println("Sms 消费者获取生产者消息msg：" + msg);
    }

}
