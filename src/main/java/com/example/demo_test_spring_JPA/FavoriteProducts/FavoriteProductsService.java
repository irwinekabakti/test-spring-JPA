package com.example.demo_test_spring_JPA.FavoriteProducts;

import java.util.Optional;
import com.example.demo_test_spring_JPA.Products.ProductsRepository;
import com.example.demo_test_spring_JPA.User.UserRepository;
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
