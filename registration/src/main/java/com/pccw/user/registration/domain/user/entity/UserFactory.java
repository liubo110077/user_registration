package com.pccw.user.registration.domain.user.entity;

import com.pccw.user.registration.domain.user.repository.UserRepositoryInterface;
import com.pccw.user.registration.infrastructure.mq.MessageQueueProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public  UserFactory(){

    }

    public  UserFactory(UserRepositoryInterface userRepository,MessageQueueProducer messageQueueProducer){
        this.userRepository = userRepository;
        this.messageQueueProducer = messageQueueProducer;
    }

    @Autowired
    private UserRepositoryInterface userRepository;
    @Autowired
    private MessageQueueProducer messageQueueProducer;


    public User create(String emailAddress,String encryptedPassword) {
        User user = new User();
        Email email = new Email(emailAddress);
        user.setEmail(email);
        user.setEncryptedPassword(encryptedPassword);
        user.setUserRepository(userRepository);
        return user;
    }


    public User create(int id, String email) {
        User user = new User(id, email);
        user.setUserRepository(userRepository);
        return user;
    }
}