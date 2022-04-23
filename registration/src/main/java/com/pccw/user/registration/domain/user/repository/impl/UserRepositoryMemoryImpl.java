package com.pccw.user.registration.domain.user.repository.impl;

import com.pccw.user.registration.domain.user.entity.User;
import com.pccw.user.registration.domain.user.repository.UserRepositoryInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Component
public class UserRepositoryMemoryImpl implements UserRepositoryInterface {

    private static final Map<Integer, User> users = new LinkedHashMap();
    private static final Set<String> emails = new HashSet();

    private static Lock saveLock = new ReentrantLock();
    private static Lock updateLock = new ReentrantLock();
    private static Lock deleteLock = new ReentrantLock();

    private static IDGenerator idGenerator = new IDGenerator();

    public boolean isEmailUsed(String email){
        if(emails.contains(email)){
            return true;
        }

        return false;
    }

    @Override
    public User save(User user) {

        if (user == null)
            throw new IllegalArgumentException("user can not be null");

        saveLock.lock();
        try {
            //prevent condition race
            final int id = idGenerator.generateID();
            user.setId(id);
            users.put(id, user);
            emails.add(user.getEmail().getEmailAddress());

        } catch (Exception e) {
            log.info("failed to register a user", e);
            throw new RuntimeException(e);
        } finally {
            saveLock.unlock();
        }

        return user;

    }

    @Override
    public User update(User user) {

        if (user == null)
            throw new IllegalArgumentException("user can not be null");


        updateLock.lock();
        try {
            int userId = user.getId();
            if (users.containsKey(userId)) {
                users.put(userId, user);

            } else {
                throw new IllegalArgumentException("user " + userId + " does not exist.");
            }
        } catch (Exception e) {
            log.info("failed to register a user", e);
            throw new RuntimeException(e);
        } finally {
            updateLock.unlock();
        }

        return user;
    }

    @Override
    public void delete(int userId) {
        if (userId <= 0)
            throw new IllegalArgumentException("user id is invalid");
        deleteLock.lock();
        try {
            if (users.containsKey(userId)) {
                users.remove(userId);
            } else {
                throw new IllegalArgumentException("user " + userId + " does not exist.");
            }
        } catch (Exception e) {
            log.info("failed to delete user", e);
            throw new RuntimeException(e);
        } finally {
            deleteLock.unlock();
        }

    }

    @Override
    public User getById(int userId) {

        if (userId <= 0)
            throw new IllegalArgumentException("user id is invalid");
        User user = null;
        if (users.containsKey(userId)) {
            user = users.get(userId);
        } else {
            throw new IllegalArgumentException("user " + userId + " does not exist.");
        }

        return user;
    }
}
