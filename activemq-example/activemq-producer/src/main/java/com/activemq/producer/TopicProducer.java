//package com.activemq.producer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsMessagingTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import javax.jms.Topic;
//
///**
// * Created by Mr.PanYang on 2018/11/1.
// *
// * 发布订阅模式生产消息
// */
//@Component
//public class TopicProducer {
//
//    @Autowired
//    private JmsMessagingTemplate jmsMessagingTemplate;
//
//    @Autowired
//    private Topic topic;
//
//    //每隔5秒向队列中发送消息
//    @Scheduled(fixedDelay = 6000)
//    public void send() {
//        String msg = System.currentTimeMillis() + "";
//        jmsMessagingTemplate.convertAndSend(topic, msg);
//        System.out.println("采用发布订阅模式，msg：" + msg);
//    }
//}
