package com.example.demo_test_spring_JPA;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class CustomResponse<K> {
    private int status;
    private String statusMessage;
    private String message;
    private K data;

    public CustomResponse(HttpStatus status, String statusMessage, String message, K data){
        this.status = status.value();
        this.statusMessage = statusMessage;
        this.message = message;
        this.data = data;
    }

    public ResponseEntity<CustomResponse<K>> toResponseEntity() {
        return ResponseEntity.status(HttpStatus.valueOf(status)).body(this);
    }
}

/*
{
  "favorite-products": [
    {
      "productId": 0,
      "id": "525c"
    },
    {
      "productId": 1,
      "id": "4b63"
    }
  ],
  "cart": [],
  "products": [
    {
      "price": 0.0032,
      "weight": 1000,
      "name": "Beetles",
      "category": "Exotic",
      "imageUrl": "/products/beetles.png",
      "metadata": {
        "unit": "g",
        "weight": 100,
        "calorie": 190,
        "proteins": 24,
        "fats": 9,
        "increment": 100,
        "carbs": 2
      },
      "id": "0"
    },
    {
      "price": 0.0033,
      "weight": 1300,
      "name": "Cucumber",
      "category": "Fresh Veggie",
      "imageUrl": "/products/cucumber.png",
      "metadata": {
        "unit": "g",
        "weight": 100,
        "calorie": 16,
        "proteins": 0.7,
        "fats": 0.1,
        "increment": 100,
        "carbs": 3.6
      },
      "id": "1"
    }
  ]
}

 */