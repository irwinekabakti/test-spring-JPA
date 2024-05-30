package com.example.demo_test_spring_JPA.controller;
import com.example.demo_test_spring_JPA.util.CustomResponse;
import com.example.demo_test_spring_JPA.service.ProductsService;
import com.example.demo_test_spring_JPA.model.Products;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

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
        CustomResponse<Iterable<Products>> response = new CustomResponse<>(HttpStatus.OK, "Success", "Products retrieved successfully!", products);
        return response.toResponseEntity();
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Products>> addProduct(@RequestBody Products product) {
        Products newProduct = productsService.addProduct(product);
        if (newProduct != null) {
            CustomResponse<Products> response = new CustomResponse<>(HttpStatus.OK, "Success", "Product added successfully!",  newProduct);
            return response.toResponseEntity();
        } else {
            CustomResponse<Products> response = new CustomResponse<>(HttpStatus.INTERNAL_SERVER_ERROR,"Failed to add product");
            return response.toResponseEntity();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Products>> deleteProduct(@PathVariable Integer id) {
        Products deletedProduct = productsService.deleteProduct(id);
        if (deletedProduct != null) {
            CustomResponse<Products> response = new CustomResponse<>(HttpStatus.OK, "Success", "Product deleted successfully!", deletedProduct);
            return response.toResponseEntity();
        } else {
            CustomResponse<Products> response = new CustomResponse<>(HttpStatus.NOT_FOUND, "Product not found");
            return response.toResponseEntity();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Products>> updateProduct(@PathVariable Integer id, @RequestBody Products product) {
        Products updatedProduct = productsService.updateProduct(id, product);
        if (updatedProduct != null) {
            CustomResponse<Products> response = new CustomResponse<>(HttpStatus.OK, "Success","Product updated successfully!", updatedProduct);
            return response.toResponseEntity();
        } else {
            CustomResponse<Products> response = new CustomResponse<>(HttpStatus.NOT_FOUND, "Product not found");
            return response.toResponseEntity();
        }
    }
}
