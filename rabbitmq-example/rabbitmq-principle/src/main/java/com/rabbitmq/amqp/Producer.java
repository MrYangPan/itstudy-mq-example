package com.rabbitmq.amqp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.MQConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Mr.PanYang on 2018/11/2.
 * <p>
 * 事物
 */
public class Producer {
    //队列名称
    private static final String QUEUE_NAME = "test_rabbit_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1 创建连接
        Connection connection = MQConnectionUtils.newConnection();
        //2 创建通道
        Channel channel = connection.createChannel();
        //3 创建队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        try {
            //开启事物
            channel.txSelect();
            String msg = "test_rabbit_yp";
            //4 发送消息
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            System.out.println("生产者投递消息内容：" + msg);
            int i = 1 / 0;
            channel.txCommit();
        } catch (Exception e) {
            System.out.println("生产者消息事物已经回滚");
            channel.txRollback();
        } finally {
            //6 关闭通道和连接
            channel.close();
            connection.close();
        }
    }
}
