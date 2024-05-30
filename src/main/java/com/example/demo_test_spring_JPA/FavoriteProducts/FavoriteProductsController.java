package com.example.demo_test_spring_JPA.FavoriteProducts;

import com.example.demo_test_spring_JPA.CustomResponse;
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
                CustomResponse<Iterable<FavoriteProducts>> response = CustomResponse.success(favorites, "User favorite products retrieved successfully.");
                return response.toResponseEntity();
            } else {
                CustomResponse<Iterable<FavoriteProducts>> response = CustomResponse.error(HttpStatus.NOT_FOUND, "User doesn't have favorite products.");
                return response.toResponseEntity();
            }
        } else {
            CustomResponse<Iterable<FavoriteProducts>> response = CustomResponse.error(HttpStatus.NOT_FOUND, "User not found.");
            return response.toResponseEntity();
        }
    }

    @PostMapping("/toggle")
    public ResponseEntity<CustomResponse<FavoriteProducts>> setFavorite(@Valid @RequestBody FavoriteProducts favorite) {
        Optional<FavoriteProducts> favoriteOpt = favoriteService.changeFavorite(favorite);
        if (favoriteOpt.isPresent()) {
            FavoriteProducts result = favoriteOpt.get();
            String message = !(favorite.getId() == null) ? "Added to favorite products." : "Removed from favorite products.";
            CustomResponse<FavoriteProducts> response = CustomResponse.success(result, message);
            return response.toResponseEntity();
        } else {
            if (!favoriteService.checkProduct(favorite.getProductId())) {
                CustomResponse<FavoriteProducts> response = CustomResponse.error(HttpStatus.NOT_FOUND, "Product not found.");
                return response.toResponseEntity();
            }
            if (!favoriteService.checkUser(favorite.getUserId())) {
                CustomResponse<FavoriteProducts> response = CustomResponse.error(HttpStatus.NOT_FOUND, "User not found.");
                return response.toResponseEntity();
            }
            CustomResponse<FavoriteProducts> response = CustomResponse.error(HttpStatus.BAD_REQUEST, "Invalid request.");
            return response.toResponseEntity();
        }
    }
}
