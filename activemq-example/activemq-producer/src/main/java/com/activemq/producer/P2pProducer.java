//package com.activemq.producer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsMessagingTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import javax.jms.Queue;
//
///**
// * Created by Mr.PanYang on 2018/11/1.
// * <p>
// * 点对点模式生产消息
// */
//@Component
//public class P2pProducer {
//
//    @Autowired
//    private JmsMessagingTemplate jmsMessagingTemplate;
//
//    @Autowired
//    private Queue queue;
//
//    //每隔5秒向队列中发送消息
//    @Scheduled(fixedDelay = 6000)
//    public void send() {
//        String msg = System.currentTimeMillis() + "";
//        jmsMessagingTemplate.convertAndSend(queue, msg);
//        System.out.println("采用点对点通讯模式，msg：" + msg);
//    }
//}
