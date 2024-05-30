package com.example.demo_test_spring_JPA.controller;

import com.example.demo_test_spring_JPA.util.CustomResponse;
import com.example.demo_test_spring_JPA.service.FavoriteProductsService;
import com.example.demo_test_spring_JPA.model.FavoriteProducts;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Validated
@RequestMapping("/api/v1/favorites")
public class FavoriteProductsController {
    private final FavoriteProductsService favoriteService;

    public FavoriteProductsController(FavoriteProductsService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Iterable<FavoriteProducts>>> getUserFavorite(@PathVariable Integer id) {
        Optional<Iterable<FavoriteProducts>> favoritesOpt = favoriteService.getUserFavorite(id);
        if (favoritesOpt.isPresent()) {
            Iterable<FavoriteProducts> favorites = favoritesOpt.get();
            if (favorites.iterator().hasNext()) {
                CustomResponse<Iterable<FavoriteProducts>> response = new CustomResponse<>(HttpStatus.OK,"Success", "User favorite products retrieved successfully.", favorites);
                return response.toResponseEntity();
            } else {
                CustomResponse<Iterable<FavoriteProducts>> response = new CustomResponse<>(HttpStatus.NOT_FOUND, "User doesn't have favorite products.");
                return response.toResponseEntity();
            }
        } else {
            CustomResponse<Iterable<FavoriteProducts>> response = new CustomResponse<>(HttpStatus.NOT_FOUND, "User not found.");
            return response.toResponseEntity();
        }
    }

    @PostMapping("/toggle")
    public ResponseEntity<CustomResponse<FavoriteProducts>> setFavorite(@Valid @RequestBody FavoriteProducts favorite) {
        Optional<FavoriteProducts> favoriteOpt = favoriteService.changeFavorite(favorite);
        if (favoriteOpt.isPresent()) {
            FavoriteProducts result = favoriteOpt.get();
            String DynamicMessage = !(favorite.getId() == null) ? "Added to favorite products." : "Removed from favorite products.";
            CustomResponse<FavoriteProducts> response = new CustomResponse<>(HttpStatus.OK,"Suceess", DynamicMessage, result);
            return response.toResponseEntity();
        } else {
            if (!favoriteService.checkProduct(favorite.getProductId())) {
                CustomResponse<FavoriteProducts> response = new CustomResponse<>(HttpStatus.NOT_FOUND, "Product not found.");
                return response.toResponseEntity();
            }
            if (!favoriteService.checkUser(favorite.getUserId())) {
                CustomResponse<FavoriteProducts> response = new CustomResponse<>(HttpStatus.NOT_FOUND, "User not found.");
                return response.toResponseEntity();
            }
            CustomResponse<FavoriteProducts> response = new CustomResponse<>(HttpStatus.BAD_REQUEST, "Invalid request.");
            return response.toResponseEntity();
        }
    }
}
