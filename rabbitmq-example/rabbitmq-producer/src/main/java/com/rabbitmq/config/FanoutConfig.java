package com.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by Mr.PanYang on 2018/11/2.
 * <p>
 * 发布订阅模式，交换机是fanout类型
 */
@Component
public class FanoutConfig {

    //邮件队列名称
    private String FANOUT_EMAIL_QUEUE = "new_fanout_email_queue";
    //消息队列名称
    private String FANOUT_SMS_QUEUE = "new_fanout_sms_queue";
    //交换机名称
    private String EXCHANGE_NAME = "fanoutExchange";

    //定义邮件队列
    @Bean
    public Queue fanoutEmailQueue() {
        return new Queue(FANOUT_EMAIL_QUEUE);
    }

    //定义消息队列
    @Bean
    public Queue fanoutSmsQueue() {
        return new Queue(FANOUT_SMS_QUEUE);
    }

    //定义交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        //路由类型交换机
//        new DirectExchange("");
        //主题类型交换机
//        new TopicExchange("");
        return new FanoutExchange(EXCHANGE_NAME);
    }

    //邮件队列和交换机绑定
    @Bean
    Binding bingingExchangeEmail(Queue fanoutEmailQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutEmailQueue).to(fanoutExchange);
    }

    //消息队列和交换机绑定
    @Bean
    Binding bingingExchangeSms(Queue fanoutSmsQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutSmsQueue).to(fanoutExchange);
    }

}
