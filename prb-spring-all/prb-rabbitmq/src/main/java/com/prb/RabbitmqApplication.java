package com.prb;

import com.prb.store.MsgMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableRabbit
@Slf4j
public class RabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args);
    }

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    MsgMap msgMap;

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                log.info("============returnedMessage====================");
                log.info("message=======:" + message);

                log.info("s===========" + s);
                log.info("s=======:" + s1);
                log.info("s2=======:" + s2);
            }
        });

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                log.info("==================confirm========================");
                log.info("correlationData:{},b:{},s:{}", correlationData, b, s);
                if (!b) {
                    try {
                        log.info("消息发送失败，稍等10s钟再次重新发送");
                        Message message = msgMap.get(correlationData.getId());
                        log.info("message is :" + message);

                        Thread.sleep(10000);
                        MessageProperties messageProperties = message.getMessageProperties();
                        Integer time = Integer.parseInt(messageProperties.getHeaders().get("times").toString());
                        if (time > 0) {
                            messageProperties.setHeader("times", time - 1);
                            rabbitTemplate.convertAndSend(message.getMessageProperties().getReceivedExchange(),
                                    message.getMessageProperties().getReceivedRoutingKey(), message, correlationData);

                        }
                        log.info("重试发送失败，丢弃消息：" + correlationData);
                        return;
                    } catch (Exception e) {

                    }
                }
                log.info("消息发送成功,删除消息map中的消息");
                msgMap.remove(correlationData.getId());
            }
        });

        return rabbitTemplate;
    }

}
