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
    FavoriteProductsService favoriteService;

    public FavoriteProductsController(FavoriteProductsService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Iterable<FavoriteProducts>>> getUserFavorite(@PathVariable Integer id) {
        Optional<Iterable<FavoriteProducts>> favoritesOpt = favoriteService.getUserFavorite(id);
        if (favoritesOpt.isPresent()) {
            Iterable<FavoriteProducts> favorites = favoritesOpt.get();
            if (favorites.iterator().hasNext()) {
                return new ResponseEntity<>(new CustomResponse<>(HttpStatus.ACCEPTED, "OK", "Get User Favorite Products", favorites), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new CustomResponse<>(HttpStatus.NOT_FOUND, "NOT_FOUND", "User doesn't have favorites", null), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(new CustomResponse<>(HttpStatus.NOT_FOUND, "NOT_FOUND", "User not found", null), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/toggle")
    public ResponseEntity<CustomResponse<FavoriteProducts>> setFavorite(@Valid @RequestBody FavoriteProducts favorite) {
        Optional<FavoriteProducts> favoriteOpt = favoriteService.changeFavorite(favorite);
        if (favoriteOpt.isPresent()) {
            FavoriteProducts result = favoriteOpt.get();
            if (favorite.getId() == null) {
                return new ResponseEntity<>(new CustomResponse<>(HttpStatus.ACCEPTED, "OK", "Added to Favorite", result), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new CustomResponse<>(HttpStatus.ACCEPTED, "OK", "Removed from Favorite", result), HttpStatus.OK);
            }
        } else {
            if (!favoriteService.checkProduct(favorite.getProductId())) {
                return new ResponseEntity<>(new CustomResponse<>(HttpStatus.NOT_FOUND, "NOT_FOUND", "Product not found", null), HttpStatus.NOT_FOUND);
            }
            if (!favoriteService.checkUser(favorite.getUserId())) {
                return new ResponseEntity<>(new CustomResponse<>(HttpStatus.NOT_FOUND, "NOT_FOUND", "User not found", null), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new CustomResponse<>(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "Invalid request", null), HttpStatus.BAD_REQUEST);
        }
    }
}
