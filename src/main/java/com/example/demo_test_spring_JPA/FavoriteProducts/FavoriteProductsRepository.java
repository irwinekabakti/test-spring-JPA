package com.example.demo_test_spring_JPA.FavoriteProducts;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteProductsRepository extends CrudRepository<FavoriteProducts, Integer>{
    Iterable<FavoriteProducts> findByUserId(Integer id);
    Optional<FavoriteProducts> findByProductId(Integer id);
}