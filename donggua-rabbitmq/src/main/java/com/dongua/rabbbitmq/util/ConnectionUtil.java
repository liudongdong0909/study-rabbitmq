package com.dongua.rabbbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 创建连接工具类
 *
 * @author IT_donggua
 * @version V1.0
 * @create 2017-03-07 下午 03:25
 */
public class ConnectionUtil {

    public static Connection getConnection() throws IOException, TimeoutException {
        // 定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        // 设置账号 用户名
        factory.setUsername("root");

        // 设置账号 密码
        factory.setPassword("root");

        // 设置Virtual Host
        factory.setVirtualHost("/rabbitmq");

        // 设置 rabbbitmq 地址
        factory.setHost("192.168.31.103");

        // 设置 rabbitmq 端口号
        factory.setPort(5672);

        // 创建连接实例
        Connection connection = factory.newConnection();

        return connection;

    }
}
