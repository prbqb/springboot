package com.prb.controller;

import com.prb.rabbit.RabbitmqHelp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/rb")
@Slf4j
public class RabbitController {

    @Autowired
    RabbitmqHelp rabbitmqHelp;

    @PostMapping("fail/send")
    public String sendMessageToFail(String msg) {

        log.info("send message to fail queue");
        rabbitmqHelp.sendMessageToFail(msg);
        return "send fail message is :";
    }

    @PostMapping("dir/send")
    public String sendMessageToDlx(String msg) {
        log.info("send message to dlx");
        rabbitmqHelp.sendMessageDirectToDlx(msg);
        return "send message to dlx : " + msg;
    }


    @PostMapping("fanout/send")
    public String sendMsgToRb(String msg) {
        log.info("send fanout msg is : " + msg);
        rabbitmqHelp.sendFanoutMessage(msg + UUID.randomUUID().toString());
        return "send msg success";
    }

    @PostMapping("topic/send")
    public String sendMsgFail(String msg, String key) {
        log.info("send topic message is: " + msg + "and key is :" + key);

        return "send topic message status is :" + rabbitmqHelp.sendTopicMessage(msg, key);
    }

    @PostMapping("error/send")
    public String sendMsgToErrorQueue(String msg) {
        rabbitmqHelp.sendMsgToErrorQueue(msg);
        return "send msg success";
    }

    @PostMapping("limit/send")
    public String sendLimitSend(String msg) {
        log.info("send limit message is ok");
        rabbitmqHelp.sendLimitSend(msg);
        return "send limit msg";
    }

    @PostMapping("overflow/send")
    public String sendOverflow(String msg) {
        log.info("send message to overflow");
        rabbitmqHelp.sendOverflow(msg);
        return "overflow message:" + msg;
    }

    @PostMapping("of/send")
    public String sendOf(String msg) {

        log.info("send of is success");
        rabbitmqHelp.sendOf(msg);
        return "send of success";
    }

}
