package com.pccw.user.registration.infrastructure.mq;

public interface MessageQueueConsumer  {

    public Message poll();
}
