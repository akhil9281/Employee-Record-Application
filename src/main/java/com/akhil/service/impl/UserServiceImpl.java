package com.akhil.service.impl;

import com.akhil.entity.User;
import com.akhil.repository.UserRepo;
import com.akhil.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User getUser(Long id){
        return userRepo.findById(id).get();
    }

    @Override
    public User updateUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
