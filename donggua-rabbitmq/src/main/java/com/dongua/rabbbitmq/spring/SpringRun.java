package com.dongua.rabbbitmq.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author IT_donggua
 * @version V1.0
 * @create 2017-03-07 下午 05:32
 */
public class SpringRun {

    public static void main(String[] args) throws  Exception{
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-rabbitmq.xml");

        RabbitTemplate template = context.getBean(RabbitTemplate.class);

        template.convertAndSend("Hello World !");

        Thread.sleep(1000);

        context.destroy();
    }
}
