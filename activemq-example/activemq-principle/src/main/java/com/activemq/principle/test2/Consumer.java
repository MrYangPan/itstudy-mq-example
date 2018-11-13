package com.activemq.principle.test2;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Mr.PanYang on 2018/10/31.
 * <p>
 * 模拟三种签收方式：1. 自动签收  2. 手动签收    3.  提交事物
 * 3.   提交事物： 生产者也需要按事物方式进行生产，消费者按事物方式消费
 */
public class Consumer {

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
        //创建会话工厂，第一种签收模式：自动签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //第二种签收模式：手动签收
//        Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
        //第三种签收方式：提交事物
//        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        //创建主题
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageConsumer consumer = session.createConsumer(topic);
        //启动消费者监听
        consumer.setMessageListener(message -> {
            try {
                TextMessage textMessage = (TextMessage) message;
                //第二种签收方式，手动签收
//                textMessage.acknowledge();
                //第三种签收方式：提交事物
//                session.commit();
                System.out.println("消费者获取到消息：text：" + textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }
}
