package com.linjia.mq.consumer.config;

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

}
