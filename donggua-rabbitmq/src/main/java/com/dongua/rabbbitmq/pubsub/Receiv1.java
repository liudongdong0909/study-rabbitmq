package com.dongua.rabbbitmq.pubsub;

import com.dongua.rabbbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 发布订阅模式 消息接受方1
 *
 * @author IT_donggua
 * @version V1.0
 * @create 2017-03-07 下午 04:27
 */
public class Receiv1 {

    private final static String QUEUE_NAME = "test_queue_ps_1";

    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws  Exception{

        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        // 同一时刻 服务器只会发一条消息给消费者
        channel.basicQos(1);

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        // 监听队列 手动返回完成
        channel.basicConsume(QUEUE_NAME, false, consumer);

        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());

            System.out.println("[x] 前台系统: " + message );
            Thread.sleep(1000);

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }


    }
}