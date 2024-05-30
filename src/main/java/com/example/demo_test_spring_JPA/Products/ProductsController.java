package com.example.demo_test_spring_JPA.Products;
import com.example.demo_test_spring_JPA.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.demo_test_spring_JPA.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public ResponseEntity<CustomResponse<Iterable<Products>>> getProducts() {
        Iterable<Products> products = productsService.getProducts();
        CustomResponse<Iterable<Products>> response = CustomResponse.success(products, "Products retrieved successfully!");
        return response.toResponseEntity();
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Products>> addProduct(@RequestBody Products product) {
        Products newProduct = productsService.addProduct(product);
        CustomResponse<Products> response = CustomResponse.success(newProduct, "Product added successfully!");
        return response.toResponseEntity();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Products>> deleteProduct(@PathVariable Integer id) {
        Products deletedProduct = productsService.deleteProduct(id);
        if (deletedProduct != null) {
            CustomResponse<Products> response = CustomResponse.success(deletedProduct, "Product deleted successfully!");
            return response.toResponseEntity();
        } else {
            CustomResponse<Products> response = CustomResponse.error(HttpStatus.NOT_FOUND, "Product not found");
            return response.toResponseEntity();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Products>> updateProduct(@PathVariable Integer id, @RequestBody Products product) {
        Products updatedProduct = productsService.updateProduct(id, product);
        if (updatedProduct != null) {
            CustomResponse<Products> response = CustomResponse.success(updatedProduct, "Product updated successfully!");
            return response.toResponseEntity();
        } else {
            CustomResponse<Products> response = CustomResponse.error(HttpStatus.NOT_FOUND, "Product not found");
            return response.toResponseEntity();
        }
    }
}
