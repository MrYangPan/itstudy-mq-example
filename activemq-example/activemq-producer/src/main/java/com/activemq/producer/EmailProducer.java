package com.activemq.producer;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * Created by Mr.PanYang on 2018/11/1.
 *
 * 采用点对点模式发送邮件
 */
@Component
public class EmailProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    //每隔5秒向队列中发送消息
    @Scheduled(fixedDelay = 6000)
    public void send() {
        String username = System.currentTimeMillis() + "";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", username);
        jsonObject.put("email", "yushengjun6442018@163.com");
        String msg = jsonObject.toString();
        jmsMessagingTemplate.convertAndSend(queue, msg);
        System.out.println("采用点对点通讯模式，msg：" + msg);
    }
}
