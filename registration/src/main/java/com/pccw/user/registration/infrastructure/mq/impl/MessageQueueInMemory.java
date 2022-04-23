package com.pccw.user.registration.infrastructure.mq.impl;

import com.pccw.user.registration.infrastructure.mq.Message;
import com.pccw.user.registration.infrastructure.mq.MessageQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
@Slf4j
public class MessageQueueInMemory implements MessageQueue {

    private static final BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>(1000);


    @Override
    public boolean offer(Message message) {
       return  queue.offer(message);
    }

    @Override
    public Message poll(){
        try {
            return queue.take();
        } catch (InterruptedException e) {
            log.error("poll from queue is interrupted",e);
        }

        return null;
    }
}
