package com.dongua.rabbbitmq.topic;

import com.dongua.rabbbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * TOPICS 主题模式 消息发送方
 * @author IT_donggua
 * @version V1.0
 * @create 2017-03-07 下午 04:38
 */
public class Send {

    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws  Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明主题模式的交换机
        channel.exchangeDeclare(EXCHANGE_NAME , "topic");

        String message = "删除商品 , id = 1003";

        // 设置 routing_key = item.delete  -> 路由键必须由 . 分隔开的词语列表
        channel.basicPublish(EXCHANGE_NAME, "item.delete", null, message.getBytes());

        System.out.println("后台系统:" + message);

        channel.close();
        connection.close();

    }

}
