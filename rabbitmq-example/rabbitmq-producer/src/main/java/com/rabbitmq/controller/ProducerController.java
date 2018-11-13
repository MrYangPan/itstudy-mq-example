package com.rabbitmq.controller;

import com.rabbitmq.producer.FanoutProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mr.PanYang on 2018/11/12.
 */
@RestController
public class ProducerController {

    @Autowired
    private FanoutProducer fanoutProducer;

    @RequestMapping("/sendMsg")
    public String sendMsg(String queueName) {
        fanoutProducer.send(queueName);
        return "success";
    }

}
