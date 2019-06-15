package com.prb.rabbit;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerLister {

    public static Integer time = 0;

    @RabbitListener(queues = "errorQueue")
    public void getErrorMessage(Message message, Channel channel) throws Exception {

        //消息重试，当超过一定次数时，让其重回队列，或者直接抛弃消息，这些代码可以在catch 代码块进行控制
        try {
            log.info("==============errorQueue=================");
            Thread.sleep(300);
            log.info("message is " + message.getBody());
            log.info("time is : " + time++);
            if (time % 10 < 5) {
                int i = 10 / 0;
            }
            //b2 true 重新回到队列 false 抛弃消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }

    }

    @RabbitListener(queues = "failQueue")
    public void getMessageError(Message message, Channel channel) throws Exception {
        log.info("==================getMessageError=================");
        Thread.sleep(10000);
        log.info("message body is " + message.getBody());
        //拒绝签收，b:true 重新回队列 false 不会重新回队列
        //channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        //签收，b:true 批量签收比当前deliveryTag 小的消息，false 只签收当前消息
        //channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        //拒绝签收 b1:true 批量拒签 false 拒签当前  b2 true 重新回到队列 false 抛弃当前消息
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);

    }

    @RabbitListener(queues = "directQueue")
    public void getDirectQueue(Message message, Channel channel) throws Exception {

        log.info("message:" + message);
        log.info("channel:" + channel);
        /*log.info("args" + args);
        log.info("channel : " + channel);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);*/

    }

    @RabbitListener(queues = "dlxQueue")
    public void getDlxQueue(Message message, Channel channel) throws Exception {

        Thread.sleep(1000);
        log.info("======================getDlxQueue==============================");
        log.info("message:" + message);
        log.info("channel:" + channel);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

    }

    @RabbitListener(queues = "prb-fanout1")
    public void getFanoutMessage1(Message message, Channel channel) throws Exception {

        Thread.sleep(1000);
        log.info("===============getFanoutMessage1============================");
        log.info("channel:" + channel);
        log.info("prb-fanout1:" + message.getBody());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "prb-fanout2")
    public void getFanoutMessage2(Message message, Channel channel) throws Exception {
        Thread.sleep(1000);
        log.info("=======================getFanoutMessage2=================================");
        log.info("channel:" + channel);
        log.info("prb-fanout2:" + message.getBody());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
