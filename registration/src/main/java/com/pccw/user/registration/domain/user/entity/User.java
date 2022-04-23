package com.pccw.user.registration.domain.user.entity;

import com.pccw.user.registration.api.exception.IncorrectPasswordException;
import com.pccw.user.registration.domain.user.repository.UserRepositoryInterface;
import com.pccw.user.registration.infrastructure.mq.EmailNotification;
import com.pccw.user.registration.infrastructure.mq.MessageQueueProducer;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

@Slf4j
@Setter
@Getter
public class User {

    private int id;

    private Email email;

    private boolean active = true;

    private String encryptedPassword;

    protected User() {

    }

    protected User(int id) {
        this.id = id;
        this.email = new Email(null);
    }

    protected User(int id, String email) {
        this.id = id;
        this.email = new Email(email);
        this.active = true;
    }

    /**
     * register a new user
     *
     * @return true if succeed , false if failed
     */

    public User register() {

        final boolean valid = this.email.isValid();

        if (!valid) {
            throw new IllegalArgumentException("invalid email, please refer to RFC 822 standards or API documentation.");
        }

        final boolean emailUsed = userRepository.isEmailUsed(this.email.getEmailAddress());
        if(emailUsed){
            throw new IllegalArgumentException("email has been used.");
        }

        final User user = userRepository.save(this);
        //send email message to MQ
        boolean succeed = messageQueueProducer.offer(EmailNotification.to(this.email.getEmailAddress()));

        if (!succeed) {
            //TODO add alert through email,phone or any other device.
            log.error("register succeed, but can not sent email to {}!", this.email.getEmailAddress());
        }

        return user;
    }


    /**
     * soft delete a user
     */
    public void delete() {
        User user = userRepository.getById(this.id);
        //soft delete
        user.inactive();
        userRepository.update(user);

    }

    /**
     * change user's email
     *
     * @param email
     * @return
     */
    public User changeEmailAddress(String email) {

        final boolean valid = Email.isValid(email);

        if (!valid) {
            throw new IllegalArgumentException("invalid email, please refer to RFC 822 standards or API documentation.");
        }

        final boolean emailUsed = userRepository.isEmailUsed(email);
        if(emailUsed){
            throw new IllegalArgumentException("email has been used.");
        }

        this.email.setEmail(email);

        User update = userRepository.update(this);
        return update;
    }

    /**
     * change password
     * @param oldPassword
     * @param newPassword
     * @return user
     */
    public User changePassword(String oldPassword, String newPassword) {

        if(!this.getEncryptedPassword().equals(DigestUtils.sha256Hex(oldPassword) )){

            throw new IncorrectPasswordException("incorrect password");
        }
        this.setEncryptedPassword(DigestUtils.sha256Hex(newPassword));
        User update = userRepository.update(this);
        return update;
    }

    //soft delete
    private void inactive() {
        this.active = false;
    }


    private UserRepositoryInterface userRepository;

    private MessageQueueProducer messageQueueProducer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }


}
