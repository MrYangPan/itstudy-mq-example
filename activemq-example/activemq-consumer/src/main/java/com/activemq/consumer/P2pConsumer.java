//package com.activemq.consumer;
//
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.stereotype.Component;
//
///**
// * Created by Mr.PanYang on 2018/11/1.
// *
// * 点对点接收消息模式
// */
//@Component
//public class P2pConsumer {
//
//    @JmsListener(destination = "${my_queue}")
//    public void receive(String msg) {
//        System.out.println("消费者通过点对点模式获取到消息：" + msg);
//    }
//
//}
