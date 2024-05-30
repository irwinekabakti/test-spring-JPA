/*
package com.example.demo_test_spring_JPA.FavoriteProducts;

import java.util.Optional;

import com.example.demo_test_spring_JPA.Products.Products;
import com.example.demo_test_spring_JPA.Products.ProductsRepository;
import com.example.demo_test_spring_JPA.User.User;
import com.example.demo_test_spring_JPA.User.UserRepository;
import com.example.demo_test_spring_JPA.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class FavoriteProductsService {
    @Autowired
    FavoriteProductsRepository favoriteProductsRepository;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    UserRepository userRepository;

    public CustomResponse<Iterable<FavoriteProducts>> getUserFavorite(Integer userId){
        if(!checkUser(userId)){
            return new CustomResponse<Iterable<FavoriteProducts>>(HttpStatus.NOT_FOUND, "NOT_FOUND", "User not found", null);
        }
        Iterable<FavoriteProducts> userFavorite = favoriteProductsRepository.findByUserId(userId);
        if(userFavorite.iterator().hasNext()){
            return new CustomResponse<Iterable<FavoriteProducts>>(HttpStatus.OK, "OK", "Get User Favorite Products", userFavorite);
        }
        return new CustomResponse<Iterable<FavoriteProducts>>(HttpStatus.NOT_FOUND, "NOT_FOUND", "user doesn't have favorite", null);
    }

    public CustomResponse<FavoriteProducts> changeFavorite(FavoriteProducts favorite){
        Optional<FavoriteProducts> checkFavorite = favoriteProductsRepository.findByProductId(favorite.getProductId());

        if(!checkProduct(favorite.getProductId())){
            return new CustomResponse<FavoriteProducts>(HttpStatus.NOT_FOUND, "NOT_FOUND", "Product not found", null);
        }
        if(!checkUser(favorite.getUserId())){
            return new CustomResponse<FavoriteProducts>(HttpStatus.NOT_FOUND, "NOT_FOUND", "User not found", null);
        }
        if(!checkFavorite.isPresent()){
            return new CustomResponse<FavoriteProducts>(HttpStatus.OK, "OK", "Added to Favorite", favoriteProductsRepository.save(favorite));
        }
        FavoriteProducts favoriteToRemove = checkFavorite.get();
        favoriteProductsRepository.deleteById(favoriteToRemove.getId());
        return new CustomResponse<FavoriteProducts>(HttpStatus.OK, "OK", "Removed from Favorite", favoriteToRemove);
    }

    private boolean checkUser(Integer userId){
        Optional<User> checkUser = userRepository.findById(userId);
        if(checkUser.isEmpty()){
            return false;
        }
        return true;
    }

    private boolean checkProduct(Integer productId){
        Optional<Products> checkProduct = productsRepository.findById(productId);
        if(checkProduct.isEmpty()){
            return false;
        }
        return true;
    }
}
 */

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
