package com.linjia.mq.producer.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author LinJia
 * @description: 生产者
 * @date 2024/8/8
 */
@Component
public class SimpleProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg) {
        rabbitTemplate.convertAndSend("DemoMQ", msg);
    }

    /**
     * @return void
     * @Description: 发布订阅模式
     * @Author LinJia
     * @Date 2024/8/13
     * @Param [msg]
     */
    public void broadcast(String msg) {
        rabbitTemplate.convertAndSend("DemoExchange", "", msg);
    }


    /**
     * @Description: 发送到指定队列
     * 每个队列都会有一个默认交换机，当不指定交换机时，消息会被发送到默认交换机，默认交换机类型为direct，路由键为队列名称
     * @Author LinJia
     * @Date 2024/8/14
     * @Param [msg]
     * @return void
     */
    public void broadcastQueue(String msg) {
        rabbitTemplate.convertAndSend("QueueExchange1", msg);
    }

}
