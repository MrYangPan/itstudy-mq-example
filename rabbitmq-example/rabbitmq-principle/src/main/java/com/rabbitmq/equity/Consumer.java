package com.rabbitmq.equity;

import com.rabbitmq.client.*;
import com.rabbitmq.utils.MQConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Mr.PanYang on 2018/10/29.
 */
public class Consumer {

    //队列名称
    private static final String QUEUE_NAME = "rabbit_queue_yp";
    //消息数量
    private static Integer msg_count = 0;

    public static void main(String[] args) throws IOException, TimeoutException {
        //1 创建连接
        Connection connection = MQConnectionUtils.newConnection();
        //2 创建通道
        Channel channel = connection.createChannel();
        //3 消费者关联队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //同一时刻服务器只会发一条消息给消费者,设置公平队列模式进行公平消费（能者多劳，谁应答快，谁就能消费更多的消息）
        channel.basicQos(1);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            //监听获取消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                msg_count++;
                String msg = new String(body, "utf-8");
                System.out.println("消费者获取生产者消息：" + msg + "这是第 " + msg_count + "   个消息");
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                } finally {
                    //手动应答模式，告诉队列服务器已经消费成功
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        //4 设置应答模式,如果为true，表示为自动应答，为false表示为手动应答（需要在监听的时候告诉队列服务器已经消费成功）
        channel.basicConsume(QUEUE_NAME, false, defaultConsumer);
        System.out.println("消费者监听模式启动1.....");
    }

}
