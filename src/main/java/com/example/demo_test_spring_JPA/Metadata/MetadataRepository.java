package com.example.demo_test_spring_JPA.Metadata;

import com.example.demo_test_spring_JPA.Products.Products;
import org.springframework.data.repository.CrudRepository;

public interface MetadataRepository extends CrudRepository<Products, Integer> {
}