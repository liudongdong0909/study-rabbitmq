package com.dongua.rabbbitmq.simple;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 接受消息方
 *
 * @author IT_donggua
 * @version V1.0
 * @create 2017-03-07 下午 03:13
 */
public class Receiv {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

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


        // 创建连接
        Connection connection = factory.newConnection();

        // 创建通道用来接受消息
        Channel channel = connection.createChannel();

        // 声明一个队列
        channel.queueDeclare("donggua_queue", false, false, false, null);

        // 定义队列消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        // 监听队列
        channel.basicConsume("donggua_queue", true, consumer);

        // 获取消息
        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("[x] Received" + message);
        }


    }

}
