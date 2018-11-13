package com.rabbitmq.equity;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.MQConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Mr.PanYang on 2018/10/29.
 * <p>
 * 生产者
 * <p>
 * 演示公平队列模式
 */
public class Producer {

    //队列名称
    private static final String QUEUE_NAME = "rabbit_queue_yp";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1 创建连接
        Connection connection = MQConnectionUtils.newConnection();
        //2 创建通道
        Channel channel = connection.createChannel();
        //3 创建队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        for (int i = 0; i < 15; i++) {
            //4 创建消息
            String msg = "yp_msg" + "_" + i;
            //5 生产者发送消息
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            System.out.println("生产者投递消息内容：" + msg);
        }
        //6 关闭通道和连接
        channel.close();
        connection.close();
    }

}
