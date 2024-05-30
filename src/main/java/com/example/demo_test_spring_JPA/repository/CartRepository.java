package com.example.demo_test_spring_JPA.repository;

import com.example.demo_test_spring_JPA.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {}