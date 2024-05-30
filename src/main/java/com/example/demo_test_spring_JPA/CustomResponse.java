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

    public static <K> CustomResponse<K> success(K data, String message) {
        return new CustomResponse<>(HttpStatus.OK, "Success", message, data);
    }

    public static <K> CustomResponse<K> error(HttpStatus status, String message) {
        return new CustomResponse<>(status, "Error", message, null);
    }

    public ResponseEntity<CustomResponse<K>> toResponseEntity() {
        return ResponseEntity.status(HttpStatus.valueOf(status)).body(this);
    }
}
