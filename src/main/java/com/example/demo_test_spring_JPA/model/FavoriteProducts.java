package com.example.demo_test_spring_JPA.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class FavoriteProducts {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @PositiveOrZero(message = "User Id Not Found")
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @NotNull
    @PositiveOrZero(message = "Product Id Not Found")
    @Column(name = "product_id", nullable = false)
    private Integer productId;
}