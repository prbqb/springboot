package com.prb.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeConfig {

    @Bean
    public DirectExchange errorExchange() {
        return new DirectExchange("dead-error-exchange");
    }

    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange("dlxExchange");
    }

    @Bean
    public FanoutExchange prbFanout() {
        return new FanoutExchange("prbFanout");
    }

    @Bean
    public TopicExchange prbTopic() {
        return new TopicExchange("prbTopic");
    }

}
