/*
package com.example.demo_test_spring_JPA.FavoriteProducts;

import java.util.Optional;
import com.example.demo_test_spring_JPA.repository.ProductsRepository;
import com.example.demo_test_spring_JPA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteProductsService {
    @Autowired
    FavoriteProductsRepository favoriteProductsRepository;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    UserRepository userRepository;

    public Optional<Iterable<FavoriteProducts>> getUserFavorite(Integer userId) {
        if (!checkUser(userId)) {
            return Optional.empty();
        }
        Iterable<FavoriteProducts> userFavorite = favoriteProductsRepository.findByUserId(userId);
        return Optional.of(userFavorite);
    }

    public Optional<FavoriteProducts> changeFavorite(FavoriteProducts favorite) {
        if (!checkProduct(favorite.getProductId()) || !checkUser(favorite.getUserId())) {
            return Optional.empty();
        }
        Optional<FavoriteProducts> checkFavorite = favoriteProductsRepository.findByProductId(favorite.getProductId());
        if (checkFavorite.isEmpty()) {
            return Optional.of(favoriteProductsRepository.save(favorite));
        }
        FavoriteProducts favoriteToRemove = checkFavorite.get();
        favoriteProductsRepository.deleteById(favoriteToRemove.getId());
        return Optional.of(favoriteToRemove);
    }

    boolean checkUser(Integer userId) {
        return userRepository.findById(userId).isPresent();
    }

    boolean checkProduct(Integer productId) {
        return productsRepository.findById(productId).isPresent();
    }
}
*/

package com.example.demo_test_spring_JPA.service;

import com.example.demo_test_spring_JPA.repository.ProductsRepository;
import com.example.demo_test_spring_JPA.repository.UserRepository;
import com.example.demo_test_spring_JPA.model.FavoriteProducts;
import com.example.demo_test_spring_JPA.repository.FavoriteProductsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoriteProductsService {
    private final FavoriteProductsRepository favoriteProductsRepository;
    private final ProductsRepository productsRepository; // Assuming there is a ProductRepository
    private final UserRepository userRepository; // Assuming there is a UserRepository

    public FavoriteProductsService(FavoriteProductsRepository favoriteProductsRepository, ProductsRepository productsRepository, UserRepository userRepository) {
        this.favoriteProductsRepository = favoriteProductsRepository;
        this.productsRepository = productsRepository;
        this.userRepository = userRepository;
    }

    public Optional<Iterable<FavoriteProducts>> getUserFavorite(Integer userId) {
        return Optional.of(favoriteProductsRepository.findByUserId(userId));
    }

    public Optional<FavoriteProducts> changeFavorite(FavoriteProducts favorite) {
        if (!checkProduct(favorite.getProductId()) || !checkUser(favorite.getUserId())) {
            return Optional.empty();
        }
        Optional<FavoriteProducts> checkFavorite = favoriteProductsRepository.findByUserIdAndProductId(favorite.getUserId(), favorite.getProductId());
        if (checkFavorite.isEmpty()) {
            return Optional.of(favoriteProductsRepository.save(favorite));
        }
        FavoriteProducts favoriteToRemove = checkFavorite.get();
        favoriteProductsRepository.deleteById(favoriteToRemove.getId());
        return Optional.of(favoriteToRemove);
    }

    public boolean checkProduct(Integer productId) {
        return productsRepository.existsById(productId);
    }

    public boolean checkUser(Integer userId) {
        return userRepository.existsById(userId);
    }
}
