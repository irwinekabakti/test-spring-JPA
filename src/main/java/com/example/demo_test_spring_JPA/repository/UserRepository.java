package com.example.demo_test_spring_JPA.repository;

import com.example.demo_test_spring_JPA.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}