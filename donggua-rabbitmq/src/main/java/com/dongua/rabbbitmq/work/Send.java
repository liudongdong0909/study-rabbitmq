package com.dongua.rabbbitmq.work;

import com.dongua.rabbbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * WORK模式 消息发送方
 *
 * @author IT_donggua
 * @version V1.0
 * @create 2017-03-07 下午 03:52
 */
public class Send {

    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        for (int i = 0; i < 200; i++) {
            String message = "我是小冬瓜" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes() );
            System.out.println("[x] 发送消息" + message);

            Thread.sleep(1000);
        }

        channel.close();
        connection.close();

    }
}
