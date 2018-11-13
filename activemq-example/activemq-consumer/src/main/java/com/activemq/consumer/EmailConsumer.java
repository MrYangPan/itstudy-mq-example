package com.activemq.consumer;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by Mr.PanYang on 2018/11/1.
 */
@Component
public class EmailConsumer {

    @Autowired
    private JavaMailSender javaMailSender;

    @JmsListener(destination = "${my_queue}")
    public void receive(String msg) {
        if (StringUtils.isEmpty(msg))
            return;
        //解析json
        JSONObject jsonObject = JSONObject.parseObject(msg);
        String userName = jsonObject.getString("userName");
        String email = jsonObject.getString("email");
        System.out.println("消费者通过点对点模式获取到消息：" + msg);
        sendSimpleMail(userName, email);
    }

    public void sendSimpleMail(String userName, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setTo(email);
        message.setSubject("测试邮件");
        message.setText("恭喜你，" + userName + "成为我们的会员!");
        javaMailSender.send(message);
        System.out.println("邮件发送成功," + JSONObject.toJSONString(message));
    }
}
