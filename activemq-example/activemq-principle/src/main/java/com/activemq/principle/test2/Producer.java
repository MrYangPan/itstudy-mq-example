package com.activemq.principle.test2;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Mr.PanYang on 2018/10/31.
 * <p>
 * 生产者,模拟发布订阅发送消息，先启动消费者然后启动生产者
 */
public class Producer {
    //这里连接的是虚拟机地址
    private static final String BROKER_URL = "tcp://192.168.151.131:61616";
    //主题名称
    private static final String TOPIC_NAME = "activemq-topic";

    public static void main(String[] args) throws JMSException {
        //获取mq连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
        //创建连接
        Connection connection = connectionFactory.createConnection();
        //启动连接
        connection.start();
        //创建会话工厂
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        //第三种签收方式：提交事物
        //Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        //创建主题
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageProducer producer = session.createProducer(topic);
        //不持久化到本地
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        for (int i = 0; i < 5; i++) {
            TextMessage textMessage = session.createTextMessage("hello activemq 我是生产者：" + i);
            producer.send(textMessage);
            //第三种签收方式：提交事物
            //  session.commit();
        }
        connection.close();
        System.out.println("生产者发送消息完毕");
    }
}
