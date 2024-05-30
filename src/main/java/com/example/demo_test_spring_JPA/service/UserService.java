package com.example.demo_test_spring_JPA.service;

import com.example.demo_test_spring_JPA.model.User;
import com.example.demo_test_spring_JPA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Iterable<User> getAllUser(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        return userRepository.save(user);
    }
}