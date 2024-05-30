package com.example.demo_test_spring_JPA.repository;

//import com.example.demo_test_spring_JPA.model.Products;
import com.example.demo_test_spring_JPA.model.Metadata;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MetadataRepository extends CrudRepository<Metadata, Integer> {
    Optional<Metadata> findByProductId(int id);
}