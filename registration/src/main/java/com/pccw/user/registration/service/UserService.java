package com.pccw.user.registration.service;

import com.pccw.user.registration.domain.user.entity.User;
import com.pccw.user.registration.domain.user.repository.UserRepositoryInterface;
import com.pccw.user.registration.domain.user.entity.UserFactory;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    UserFactory userFactory;

    @Autowired
    UserRepositoryInterface userRepository;

    @Transactional
    public User register(String email,String encryptedPassword) {


        final User user = userFactory.create(email,encryptedPassword);
        return user.register();
    }

    @Transactional
    public void delete(int userId) {

        final User user = userRepository.getById(userId);
        user.delete();

    }

    @Transactional
    public void batchDelete(int[] userIds) {

        final ArrayList<IllegalArgumentException> illegalArgumentExceptions = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        for (int userId : userIds) {
            try {
                final User user = userRepository.getById(userId);
                user.delete();
            } catch (IllegalArgumentException e) {
                illegalArgumentExceptions.add(e);
            }
        }

        if (!illegalArgumentExceptions.isEmpty()) {
            for (IllegalArgumentException exception :
                    illegalArgumentExceptions) {

                builder.append(exception.getMessage());
            }
            final String message = builder.toString();

            throw new IllegalArgumentException(message);
        }


    }

    public User findById(int userId) {
        final User user = userRepository.getById(userId);
        return user;
    }

    @Transactional
    public User changeEmail(int userId, String email) {
        final User user = userRepository.getById(userId);
        return user.changeEmailAddress(email);
    }

    @Transactional
    public User changePassword(int userId, String oldPassword,String newPassword) {
        final User user = userRepository.getById(userId);
        return user.changePassword(oldPassword,newPassword);
    }

}
