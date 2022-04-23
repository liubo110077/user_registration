package com.pccw.user.registration.infrastructure.mq.impl;

import com.pccw.user.registration.infrastructure.mq.MessageQueueConsumer;
import com.pccw.user.registration.infrastructure.mq.Message;
import com.pccw.user.registration.infrastructure.mq.MessageQueue;
import org.springframework.stereotype.Component;

@Component
public class MessageQueueConsumerInMemory implements MessageQueueConsumer {

    private MessageQueue queue;

    public MessageQueueConsumerInMemory(MessageQueue queue){

        this.queue= queue;

    }
    @Override
    public Message poll(){
        return queue.poll();
    }
}
