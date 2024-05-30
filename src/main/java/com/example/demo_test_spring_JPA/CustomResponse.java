package com.example.demo_test_spring_JPA;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomResponse<K> {
    private String message;
    private int statusCode;
    private K data;

    public CustomResponse(String message, int statusCode, K data) {
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
    }
}