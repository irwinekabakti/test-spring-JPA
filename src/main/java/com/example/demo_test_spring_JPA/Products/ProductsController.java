package com.example.demo_test_spring_JPA.Products;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
    ProductsService productsService;

    public ProductsController (ProductsService productService){
        this.productsService = productService;
    }

    @GetMapping
    public Iterable<Products> getProduct() {
        return productsService.getProducts();
    }

    @PostMapping
    public Products addProduct(@RequestBody Products product) {
        return productsService.addProduct(product);
    }

    @DeleteMapping("/clear")
    public String clearProduct() {
        return productsService.clearProduct();
    }

    @DeleteMapping("/{id}")
    public Products deleteProduct(@PathVariable Integer id) {
        return productsService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public Products updateProduct(@PathVariable Integer id, @RequestBody Products product) {
        return productsService.updateProduct(id, product);
    }
}
