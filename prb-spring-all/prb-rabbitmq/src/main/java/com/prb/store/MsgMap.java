package com.prb.store;

import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class MsgMap {

    public static ConcurrentHashMap<String, Message> messages = new ConcurrentHashMap<>();

    public Message get(String msgId) {
        return messages.get(msgId);
    }

    public void save(String msgId, Message message) {

        messages.put(msgId, message);
    }

    public void remove(String msgId) {
        messages.remove(msgId);
    }
}
