package com.prb.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueueConfig {

    /**
     * 死信队列 交换机标识符
     */
    public static final String DEAD_LETTER_QUEUE_KEY = "x-dead-letter-exchange";
    /**
     * 死信队列交换机绑定键标识符
     */
    public static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";


    @Bean
    public Queue failQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put(DEAD_LETTER_QUEUE_KEY, "dead-error-exchange");
        args.put(DEAD_LETTER_ROUTING_KEY, "dead-error-key");
        return new Queue("failQueue", false, false, false, args);
    }

    @Bean
    public Queue directQueue() {
        Map<String, Object> args = new HashMap<>(2);
        args.put(DEAD_LETTER_QUEUE_KEY, "dlxExchange");
        args.put(DEAD_LETTER_ROUTING_KEY, "dlx-key");
        args.put("x-message-ttl", 10000);
        return new Queue("directQueue", false, false, false, args);
    }

    @Bean
    public Queue errorQueue() {
        return new Queue("errorQueue");
    }

    @Bean
    public Queue dlxQueue() {
        return new Queue("dlxQueue");
    }

    @Bean
    public Queue fanoutQueue1() {
        return new Queue("prb-fanout1");
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("prb-fanout2");
    }

    @Bean
    public Queue topicQueue1() {
        return new Queue("prb.topic1");
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue("prb.topic2");
    }

    @Bean
    public Queue topicQueue3() {
        return new Queue("prb.plus.topic3");
    }

    @Bean
    public Queue topicQueue4() {
        return new Queue("prb.plus.topic4");
    }


}
