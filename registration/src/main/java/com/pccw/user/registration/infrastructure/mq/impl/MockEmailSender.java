package com.pccw.user.registration.infrastructure.mq.impl;

import com.pccw.user.registration.infrastructure.mq.EmailNotification;
import com.pccw.user.registration.infrastructure.mq.MessageQueueConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class MockEmailSender {

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        //Mock sending email
        new Consumer().start();

    }

    @Autowired
    MessageQueueConsumer consumer;

    class Consumer extends Thread{

        @Override
        public void run() {
            while (true){
                EmailNotification message = (EmailNotification) consumer.poll();
                //data desensitization
                log.info("sending email to {}",message.getTo().substring(3));
            }
        }
    }
}
