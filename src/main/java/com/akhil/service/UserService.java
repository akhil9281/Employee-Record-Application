package com.akhil.service;

import com.akhil.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User saveUser(User user);

    User getUser(Long id);

    User updateUser(User user);

    void deleteUser(Long id);
}
