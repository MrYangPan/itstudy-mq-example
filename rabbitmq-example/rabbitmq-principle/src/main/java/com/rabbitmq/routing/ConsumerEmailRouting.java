package com.rabbitmq.routing;

import com.rabbitmq.utils.MQConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Mr.PanYang on 2018/10/30.
 * <p>
 * 邮件消费者
 */
public class ConsumerEmailRouting {

    //队列名称
    private static final String EMAIL_QUEUE = "email_queue_routing";
    //交换机名称
    private static String DESTINATION_NAME = "my_routing_destination";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1 建立连接
        Connection connection = MQConnectionUtils.newConnection();
        //2 创建通道
        Channel channel = connection.createChannel();
        //3 声明队列
        channel.queueDeclare(EMAIL_QUEUE, false, false, false, null);
        //4 消费者队列绑定交换机,可以接受短信和邮件
        channel.queueBind(EMAIL_QUEUE, DESTINATION_NAME, "email");
        channel.queueBind(EMAIL_QUEUE, DESTINATION_NAME, "sms");
        //5 监听消息
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("Email消费者获取生产者消息：" + msg);
            }
        };
        //6 设置应答模式,如果为true，表示为自动应答，为false表示为手动应答（需要在监听的时候告诉队列服务器已经消费成功）
        channel.basicConsume(EMAIL_QUEUE, true, defaultConsumer);
        System.out.println("Email消费者监听模式启动.....");
    }
}
