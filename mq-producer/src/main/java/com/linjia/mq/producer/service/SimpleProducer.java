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
        rabbitTemplate.convertAndSend("queue0", msg);
    }

    /**
     * @Description: 广播模式
     * @Author LinJia
     * @Date 2024/8/13
     * @Param [msg]
     * @return void
     */
   public void broadcast(String msg) {
       rabbitTemplate.convertAndSend("DemoExchange","", msg);
    }

}
