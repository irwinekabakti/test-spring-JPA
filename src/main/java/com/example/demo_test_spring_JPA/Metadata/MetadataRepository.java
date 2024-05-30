package com.example.demo_test_spring_JPA.Metadata;

//import com.example.demo_test_spring_JPA.Products.Products;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MetadataRepository extends CrudRepository<Metadata, Integer> {
    Optional<Metadata> findByProductId(int id);
}