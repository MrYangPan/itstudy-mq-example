package com.rabbitmq.amqp;

import com.rabbitmq.client.*;
import com.rabbitmq.utils.MQConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Mr.PanYang on 2018/11/2.
 */
public class Consumer {

    //队列名称
    private static final String QUEUE_NAME = "test_rabbit_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1 建立连接
        Connection connection = MQConnectionUtils.newConnection();
        //2 创建通道
        Channel channel = connection.createChannel();
        //3 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //4 监听消息
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("Email消费者获取生产者消息：" + msg);
            }
        };
        //5 设置应答模式,如果为true，表示为自动应答，为false表示为手动应答（需要在监听的时候告诉队列服务器已经消费成功）
        channel.basicConsume(QUEUE_NAME, true, defaultConsumer);
        System.out.println("消费者监听模式启动.....");
    }
}
