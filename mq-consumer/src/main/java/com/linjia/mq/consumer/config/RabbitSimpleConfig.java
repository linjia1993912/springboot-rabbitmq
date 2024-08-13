package com.linjia.mq.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LinJia
 * @description: TODO
 * @date 2024/8/8
 */
@Configuration
public class RabbitSimpleConfig {

    /**
     * 设置一个简单的队列
     */
    @Bean
    public Queue queue() {
        return new Queue("DemoMQ");
    }

    /**
     * 声明交换机
     *
     * @return 交换机
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("DemoExchange");
    }

    /**
     * 声音一个队列，用于绑定到交换机
     *
     * @return 交换机
     */
    @Bean
    public Queue queueExchange1() {
        return new Queue("QueueExchange1");
    }

    /**
     * 声音一个队列，用于绑定到交换机
     *
     * @return 交换机
     */
    @Bean
    public Queue queueExchange2() {
        return new Queue("QueueExchange2");
    }


    /**
     * 把queueExchange队列绑定到交换机
     *
     * @return 交换机
     */
    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queueExchange1()).to(fanoutExchange());
    }

    /**
     * 把queueExchange队列绑定到交换机
     *
     * @return 交换机
     */
    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queueExchange2()).to(fanoutExchange());
    }


}
