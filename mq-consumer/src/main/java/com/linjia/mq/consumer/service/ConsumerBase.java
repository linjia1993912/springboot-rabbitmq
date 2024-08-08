package com.linjia.mq.consumer.service;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author LinJia
 * @description: 消费者-基础
 * @date 2024/8/8
 */
@Component
@Slf4j
public class ConsumerBase {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @Description: 简单消费者，无限流、无并发
     * @Author LinJia
     * @Date 2024/8/8
     * @Param [msg]
     * @return void
     */
    //@RabbitListener(queues = "DemoMQ")
    public void receive(String msg) {
        log.info("receive msg: {}", msg);
    }

}
