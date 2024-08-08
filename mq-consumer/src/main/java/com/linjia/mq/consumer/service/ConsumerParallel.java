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
 * @description: 消费者-并行消费
 * 一次可并行消费多条消息
 * @date 2024/8/8
 */
@Component
@Slf4j
public class ConsumerParallel {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @Description: 多线程并行消费
     * 在2.0版本后，可以在注解中配置该参数,次方式为注解上增加concurrency属性，不太推荐，扩展性不高
     * 可以在配置文件中配置，部署时需要修改配置文件中的concurrency属性
     * @Author LinJia
     * @Date 2024/8/8
     * @Param [msg]
     * @return void
     */
    //@RabbitListener(queues = "DemoMQ",concurrency = "5-10")
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


    /**
     * @Description: concurrency: 2 prefetch: 5
     * 即会开启2个线程去消费消息，每个线程都会抓取5个消息到内存中（注意不是两个线程去共享内存中抓取的消息）。
     * @Author LinJia
     * @Date 2024/8/8
     * @Param [msg]
     * @return void
     */
    //@RabbitListener(queues = "DemoMQ")
    public void receiveACK2(Message message, Channel channel) {
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
