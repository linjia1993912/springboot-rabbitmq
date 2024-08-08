package com.linjia.mq.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

/**
 * @author LinJia
 * @description: 消费者-公平分发
 * 能者多劳
 * 根据消费者的消费能力进行公平分发，处理快的处理的多，处理慢的处理的少；按劳分配；
 * @date 2024/8/8
 */
@Component
@Slf4j
public class ConsumerFair {

    @Autowired
    private RabbitTemplate rabbitTemplate;

/*    @Value("${spring.rabbitmq.listener.simple.concurrency}")
    private int concurrency;

    @Value("${spring.rabbitmq.listener.simple.max-concurrency}")
    private int maxConcurrency;

    @Value("${spring.rabbitmq.listener.simple.prefetch}")
    private int prefetch;*/

/*    @PostConstruct
    public void validateConfig() {
        System.out.println("Concurrency: " + concurrency);
        System.out.println("Max Concurrency: " + maxConcurrency);
        System.out.println("prefetch: " + prefetch);
    }*/


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
            //消费者一次接收一条消息
            channel.basicQos(1);
            String msg = new String(message.getBody());
            log.info("消费者1 receive msg: {}", msg);

            Thread.sleep(10000);

            //basicAck方法参数的解释如下：
            //deliveryTag：消息投递ID，要签收的投递ID。
            //multiple：是否批量签收。
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //模拟第二个消费者，实现公平分发，能者多劳
    //公平分发需要消费者开启手动签收
    //@RabbitListener(queues = "DemoMQ")
    public void receiveACK2(Message message, Channel channel) {
        try {
            //消费者一次接收一条消息
            channel.basicQos(1);
            String msg = new String(message.getBody());
            log.info("消费者2 receive msg: {}", msg);
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
