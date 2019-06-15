package com.prb.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Exchange2QueueConfig {

    @Bean
    public Binding errorBinding(Queue errorQueue, DirectExchange errorExchange) {

        return BindingBuilder.bind(errorQueue).to(errorExchange).with("dead-error-key");
    }

    @Bean
    public Binding directBinding(Queue dlxQueue, DirectExchange dlxExchange) {

        return BindingBuilder.bind(dlxQueue).to(dlxExchange).with("dlx-key");
    }

    @Bean
    public Binding fanoutBinding1(Queue fanoutQueue1, FanoutExchange prbFanout) {
        return BindingBuilder.bind(fanoutQueue1).to(prbFanout);
    }

    @Bean
    public Binding fanoutBinding2(Queue fanoutQueue2, FanoutExchange prbFanout) {
        return BindingBuilder.bind(fanoutQueue2).to(prbFanout);
    }

    @Bean
    public Binding topicBinding1(Queue topicQueue1, TopicExchange prbTopic) {

        return BindingBuilder.bind(topicQueue1).to(prbTopic).with("prb.top");
    }

    @Bean
    public Binding topicBinding2(Queue topicQueue2, TopicExchange prbTopic) {

        return BindingBuilder.bind(topicQueue2).to(prbTopic).with("prb.#");
    }

    @Bean
    public Binding topicBinding3(Queue topicQueue3, TopicExchange prbTopic) {

        return BindingBuilder.bind(topicQueue3).to(prbTopic).with("prb.*");
    }

    @Bean
    public Binding topicBinding4(Queue topicQueue4, TopicExchange prbTopic) {

        return BindingBuilder.bind(topicQueue4).to(prbTopic).with("prb.plus.#");
    }


}
