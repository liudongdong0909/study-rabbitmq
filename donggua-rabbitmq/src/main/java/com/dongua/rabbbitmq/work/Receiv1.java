package com.dongua.rabbbitmq.work;

import com.dongua.rabbbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/**
 * WORK模式 消息接受方1
 *
 * @author IT_donggua
 * @version V1.0
 * @create 2017-03-07 下午 04:00
 */
public class Receiv1 {
    public static final String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws  Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        // 监听队列 手动返回完成
        channel.basicConsume(QUEUE_NAME, false, queueingConsumer);

        while (true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("[X] Received" + message);

            Thread.sleep(10);

            // 返回确认状态
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

        }
    }
}
