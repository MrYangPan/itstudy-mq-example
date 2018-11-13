package com.rabbitmq.routing;

import com.rabbitmq.utils.MQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Mr.PanYang on 2018/10/30.
 * <p>
 * 使用交换机，路由模式
 */
public class RoutingProducer {

    //交换机名称
    private static String DESTINATION_NAME = "my_routing_destination";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1 建立连接
        Connection connection = MQConnectionUtils.newConnection();
        //2 创建通道
        Channel channel = connection.createChannel();
        //3 生产者绑定交换机，参数1：交换机名称、参数2：交换机类型
        channel.exchangeDeclare(DESTINATION_NAME, "direct");
        //4 创建消息
        String msg = "my_routing_destination_msg";
        //5 发送消息
        channel.basicPublish(DESTINATION_NAME, "sms", null, msg.getBytes());
        //6 关闭通道和连接
        channel.close();
        connection.close();
    }
}
