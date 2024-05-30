package com.example.demo_test_spring_JPA.Products;
import com.example.demo_test_spring_JPA.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
    ProductsService productsService;

    public ProductsController (ProductsService productService){
        this.productsService = productService;
    }

    @GetMapping
    public CustomResponse<Iterable<Products>> getProducts() {
        Iterable<Products> products = productsService.getProducts();
        return new CustomResponse<>(HttpStatus.OK,"Success", "Mantap Bang !", products);
    }

    @PostMapping
    public CustomResponse<Products> addProduct(@RequestBody Products product) {
        Products newProduct = productsService.addProduct(product);
        return new CustomResponse<>(HttpStatus.OK, "Product added successfully", "Mantap Bang !", newProduct);
    }

//    @DeleteMapping("/clear")
//    public CustomResponse<String> clearProducts() {
//        String result = productsService.clearProduct();
//        return new CustomResponse<>(result, 200, null);
//    }

    @DeleteMapping("/{id}")
    public CustomResponse<Products> deleteProduct(@PathVariable Integer id) {
        Products deletedProduct = productsService.deleteProduct(id);
        if (deletedProduct != null) {
            return new CustomResponse<>(HttpStatus.OK, "Product deleted successfully", "Mantap Bang !", deletedProduct);
        } else {
            return new CustomResponse<>(HttpStatus.NOT_FOUND, "Product not found", "Mantap Bang !", null);
        }
    }

    @PutMapping("/{id}")
    public CustomResponse<Products> updateProduct(@PathVariable Integer id, @RequestBody Products product) {
        Products updatedProduct = productsService.updateProduct(id, product);
        if (updatedProduct != null) {
            return new CustomResponse<>(HttpStatus.OK, "Product updated successfully", "Mantap Bang !", updatedProduct);
        } else {
            return new CustomResponse<>(HttpStatus.NOT_FOUND,"Product not found", "Mantap Bang !", null);
        }
    }
}
