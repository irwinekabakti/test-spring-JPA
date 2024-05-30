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

    // Constructors for error responses
    public CustomResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.statusMessage = status.getReasonPhrase();
        this.message = message;
        this.data = null;
    }

    // Method to convert CustomResponse to ResponseEntity
    public ResponseEntity<CustomResponse<K>> toResponseEntity() {
        return ResponseEntity.status(HttpStatus.valueOf(status)).body(this);
    }
}