package com.pccw.user.registration;

import ch.qos.logback.classic.Logger;
import com.pccw.user.registration.infrastructure.mq.EmailNotification;
import com.pccw.user.registration.infrastructure.mq.Message;
import com.pccw.user.registration.infrastructure.mq.MessageQueue;
import com.pccw.user.registration.infrastructure.mq.MessageQueueConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Slf4j
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
