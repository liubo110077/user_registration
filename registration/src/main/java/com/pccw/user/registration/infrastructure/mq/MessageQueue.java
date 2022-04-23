package com.pccw.user.registration.infrastructure.mq;

public interface MessageQueue {

    public boolean offer(Message message);

    public Message poll();

}
