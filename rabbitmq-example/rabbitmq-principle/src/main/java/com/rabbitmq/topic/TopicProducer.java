package com.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.MQConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Mr.PanYang on 2018/11/2.
 * <p>
 * 交换机：topic 模式
 */
public class TopicProducer {
    //交换机名称
    private static String DESTINATION_NAME = "my_topic_destination";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1 建立连接
        Connection connection = MQConnectionUtils.newConnection();
        //2 创建通道
        Channel channel = connection.createChannel();
        //3 生产者绑定交换机，参数1：交换机名称、参数2：交换机类型
        channel.exchangeDeclare(DESTINATION_NAME, "topic");
        String routingKey = "log.email";
        //4 创建消息
        String msg = "my_topic_destination_msg" + routingKey;
        //5 发送消息
        channel.basicPublish(DESTINATION_NAME, routingKey, null, msg.getBytes());
        //6 关闭通道和连接
        channel.close();
        connection.close();
    }
}
