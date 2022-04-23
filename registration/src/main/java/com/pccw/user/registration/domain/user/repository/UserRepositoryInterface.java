package com.pccw.user.registration.domain.user.repository;

import com.pccw.user.registration.domain.user.entity.User;

public interface UserRepositoryInterface {


    public User save(User user);

    public User update(User user);

    public void delete(int id);

    public User getById(int id);

    public boolean isEmailUsed(String email);
}
