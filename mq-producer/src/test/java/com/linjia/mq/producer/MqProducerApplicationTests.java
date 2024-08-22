package com.linjia.mq.producer;

import com.linjia.mq.producer.service.SimpleProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class MqProducerApplicationTests {

    @Autowired
    private SimpleProducer simpleProducer;

    @Test
    void hellMQ() {
        for (int i = 0; i < 100; i++) {
            simpleProducer.send("消息" + i);
        }
        try {
            // 阻塞进程，使消费者能够正常监听消费
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return void
     * @Description: 测试广播模式
     * @Author LinJia
     * @Date 2024/8/13
     * @Param []
     */
    @Test
    void broadcast() {
        simpleProducer.broadcast("这是一个广播消息");
        try {
            // 阻塞进程，使消费者能够正常监听消费
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 发布订阅模式
     * @Author LinJia
     * @Date 2024/8/14
     * @Param []
     * @return void
     */
    @Test
    void broadcastQueue() {
        simpleProducer.broadcastQueue("这是一个广播到指定队列的消息");
        try {
            // 阻塞进程，使消费者能够正常监听消费
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
