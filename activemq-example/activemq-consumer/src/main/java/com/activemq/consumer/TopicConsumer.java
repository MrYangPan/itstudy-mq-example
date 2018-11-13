//package com.activemq.consumer;
//
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.stereotype.Component;
//
///**
// * Created by Mr.PanYang on 2018/11/1.
// * <p>
// * 发布订阅模式接收消息模式
// */
//@Component
//public class TopicConsumer {
//
//    @JmsListener(destination = "${my_topic}")
//    public void receive(String msg) {
//        System.out.println("消费者通过发布订阅模式获取到消息：" + msg);
//    }
//
//}
