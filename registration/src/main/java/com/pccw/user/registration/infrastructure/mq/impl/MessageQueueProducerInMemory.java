package com.pccw.user.registration.infrastructure.mq.impl;

import com.pccw.user.registration.infrastructure.mq.Message;
import com.pccw.user.registration.infrastructure.mq.MessageQueue;
import com.pccw.user.registration.infrastructure.mq.MessageQueueProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageQueueProducerInMemory implements MessageQueueProducer {

    @Autowired
    private MessageQueue queue;

    public MessageQueueProducerInMemory(MessageQueue queue){

        this.queue= queue;
    }

    @Override
    public boolean offer(Message message){
        return queue.offer(message);
    }
}
