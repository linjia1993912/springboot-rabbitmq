package com.linjia.mq.consumer.service;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author LinJia
 * @description: 消费者-发布订阅模式
 * 在Spring Boot中集成RabbitMQ并实现发布订阅模式，可以使用RabbitMQ的Fanout Exchange类型。
 * Fanout Exchange将接收到的消息广播给绑定到该交换机的所有队列，从而实现发布订阅模式，确保所有消费者都能收到消息。
 * @date 2024/8/13
 */
@Component
public class ConsumerBroadcast {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("QueueExchange1"),
            exchange = @Exchange(name = "DemoExchange", type = ExchangeTypes.FANOUT)
    ))
    public void receive1(String message) {
        System.out.println("消费者【1】接收到消息：" + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("QueueExchange2"),
            exchange = @Exchange(name = "DemoExchange", type = ExchangeTypes.FANOUT)
    ))
    public void receive2(String message) {
        System.out.println("消费者【2】接收到消息：" + message);
    }
}
