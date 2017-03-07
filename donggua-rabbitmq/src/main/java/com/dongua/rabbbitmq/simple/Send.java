package com.dongua.rabbbitmq.simple;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息发送方
 *
 * @author IT_donggua
 * @version V1.0
 * @create 2017-03-07 下午 02:42
 */
public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {
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

        // 创建连接通道 通道用来发送消息
        Channel channel = connection.createChannel();


        // 声明一个队列
        channel.queueDeclare("donggua_queue", false, false, false, null);

        //定义一个消息
        String message = "我 是 一个 大 冬瓜";

        // 发送消息
        channel.basicPublish("", "donggua_queue", null, message.getBytes());

        System.out.println("[x]" + "---" + message );

        // 断开连接 只需要 关闭通道和连接
        channel.close();
        connection.close();

    }

}
