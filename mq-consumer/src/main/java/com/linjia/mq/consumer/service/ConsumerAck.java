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
 * @description: 消费者-手动签收
 * @date 2024/8/8
 */
@Component
@Slf4j
public class ConsumerAck {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @return void
     * @Description: 手动签收确认消息
     * @Author LinJia
     * @Date 2024/8/8
     * @Param [msg]
     */
    //@RabbitListener(queues = "DemoMQ")
    public void receiveACK(Message message, Channel channel) {
        try {
            String msg = new String(message.getBody());
            log.info("消费者 receive msg: {}", msg);

            Thread.sleep(1000);

            //basicAck方法参数的解释如下：
            //deliveryTag：消息投递ID，要签收的投递ID。
            //multiple：是否批量签收。
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
