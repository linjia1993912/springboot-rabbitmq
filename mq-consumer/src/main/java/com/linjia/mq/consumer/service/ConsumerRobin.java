package com.linjia.mq.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author LinJia
 * @description: 消费者-轮询模式
 * 该模式接收消息是当有多个消费者接入时，消息的分配模式是一个消费者分配一条，直至消息消费完成；
 * @date 2024/8/8
 */
@Component
@Slf4j
public class ConsumerRobin {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @Description: 消费者1
     * @Author LinJia
     * @Date 2024/8/8
     * @Param [msg]
     * @return void
     */
    //@RabbitListener(queues = "DemoMQ")
    public void receive(String msg) {
        log.info("消费者1 receive msg: {}", msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description:消费者2
     * @Author LinJia
     * @Date 2024/8/8
     * @Param [msg]
     * @return void
     */
    //@RabbitListener(queues = "DemoMQ")
    public void receive2(String msg) {
        log.info("消费者2 receive msg: {}", msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
