package com.rabbitmq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Mr.PanYang on 2018/11/2.
 */
public class MQConnectionUtils {

    //创建新的MQ
    public static Connection newConnection() throws IOException, TimeoutException {
        //1 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        //2 设置连接地址
        factory.setHost("127.0.0.1");
        //3 设置用户
        factory.setUsername("admin_yp");
        //4 设置密码
        factory.setPassword("123456");
        //5 设置端口号
        factory.setPort(5672);
        //6 设置VirtualHost地址
        factory.setVirtualHost("/admin_host");
        Connection connection = factory.newConnection();
        return connection;
    }

}
