package com.dongua.rabbbitmq.routing;

import com.dongua.rabbbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 路由模式 消息发送方
 *
 * @author IT_donggua
 * @version V1.0
 * @create 2017-03-07 下午 05:12
 */
public class Send {
    private final static String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明 direct 类型的交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String message  = "删除商品 id=1009";
        String message2 = "更新商品 id=1019";
        String message3 = "新增商品 id=1029";

        channel.basicPublish(EXCHANGE_NAME, "delete", null, message.getBytes());
        channel.basicPublish(EXCHANGE_NAME, "update", null, message2.getBytes());
        channel.basicPublish(EXCHANGE_NAME, "insert", null, message3.getBytes());

        System.out.println("后台系统：" + message + "===" + message2 + "===" + message3);

        channel.close();
        connection.close();
    }
}
