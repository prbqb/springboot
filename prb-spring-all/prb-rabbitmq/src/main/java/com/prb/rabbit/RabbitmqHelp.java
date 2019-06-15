package com.prb.rabbit;

import com.alibaba.fastjson.JSONObject;
import com.prb.store.MsgMap;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageBuilderSupport;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RabbitmqHelp {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    MsgMap msgMap;

    public Boolean sendMessageToFail(String msg) {

        Message message = MessageBuilder.withBody(msg.getBytes()).setMessageId(UUID.randomUUID().toString())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding("utf-8").build();

        rabbitTemplate.convertAndSend("failQueue", message, new CorrelationData());
        return true;
    }

    public Boolean sendMessageDirectToDlx(String msg) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "xx@163.com");
        jsonObject.put("timestamp", 0);
        jsonObject.put("message", msg);
        String jsonString = jsonObject.toJSONString();
        System.out.println("jsonString:" + jsonString);
        // 设置消息唯一id 保证每次重试消息id唯一
        Message message = MessageBuilder.withBody(jsonString.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding("utf-8")
                .setMessageId(UUID.randomUUID() + "").build(); //消息id设置在请求头里面 用UUID做全局ID
        rabbitTemplate.convertAndSend("directQueue", message, new CorrelationData(message.getMessageProperties().getMessageId()));

        msgMap.save(message.getMessageProperties().getMessageId(), message);

        return true;
    }

    public boolean sendFanoutMessage(String msg) {

        Message message = MessageBuilder.withBody(msg.getBytes()).setMessageId(UUID.randomUUID().toString())
                .setContentEncoding(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding("utf-8").build();
        rabbitTemplate.convertAndSend("prbFanout", "", message, new CorrelationData(message.getMessageProperties().getMessageId()));
        msgMap.save(message.getMessageProperties().getMessageId(), message);

        return true;
    }

    public Boolean sendTopicMessage(String msg, String key) {

        Message message = MessageBuilder.withBody(msg.getBytes()).setMessageId(UUID.randomUUID().toString())
                .setContentEncoding("utf-8").setContentType(MessageProperties.CONTENT_TYPE_JSON).build();
        rabbitTemplate.convertAndSend("prbTopic", key, message, new CorrelationData(message.getMessageProperties().getMessageId()));
        msgMap.save(message.getMessageProperties().getMessageId(), message);
        return true;
    }


    public Boolean sendMsgToErrorQueue(String msg) {

        Message message = MessageBuilder.withBody(msg.getBytes()).setMessageId(UUID.randomUUID().toString())
                .setContentEncoding(MessageProperties.CONTENT_TYPE_JSON).setContentType("utf-8").build();
        rabbitTemplate.convertAndSend("failQueue", message, new CorrelationData(message.getMessageProperties().getMessageId()));
        msgMap.save(message.getMessageProperties().getMessageId(), message);
        return true;

    }


    public Boolean sendLimitSend(String msg) {

        Message message = MessageBuilder.withBody(msg.getBytes()).setMessageId(UUID.randomUUID().toString())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding("utf-8").build();
        rabbitTemplate.convertAndSend("limit-queue", message, new CorrelationData(message.getMessageProperties().getMessageId()));
        msgMap.save(message.getMessageProperties().getMessageId(), message);
        return true;
    }

    public Boolean sendOverflow(String msg) {

        Message message = MessageBuilder.withBody(msg.getBytes()).setMessageId(UUID.randomUUID().toString())
                .setContentEncoding("utf-8").setContentType(MessageProperties.CONTENT_TYPE_JSON).build();
        message.getMessageProperties().setReceivedRoutingKey("overflow-queue-other");
        message.getMessageProperties().setHeader("times", 3);
        msgMap.save(message.getMessageProperties().getMessageId(), message);
        rabbitTemplate.convertAndSend("overflow-queue-other", message, new CorrelationData(message.getMessageProperties().getMessageId()));

        return true;
    }

    public Boolean sendOf(String msg) {

        Message message = MessageBuilder.withBody(msg.getBytes()).setMessageId(UUID.randomUUID().toString())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding("utf-8").build();
        rabbitTemplate.convertAndSend("overflow-queue", message, new CorrelationData(message.getMessageProperties().getMessageId()));
        msgMap.save(message.getMessageProperties().getMessageId(), message);
        return true;
    }

}
