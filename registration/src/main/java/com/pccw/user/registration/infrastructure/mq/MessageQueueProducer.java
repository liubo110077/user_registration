package com.pccw.user.registration.infrastructure.mq;


public interface MessageQueueProducer {

    public boolean offer(Message message);
}
