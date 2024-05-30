package com.example.demo_test_spring_JPA.Products;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productRepository) {
        this.productsRepository = productRepository;
    }

    public Iterable<Products> getProducts() {
        return productsRepository.findAll();
    }

    public Products addProduct(Products product) {
        return  productsRepository.save(product);
    }

    public Products deleteProduct(Integer id) {
        Optional<Products> itemToRemove = productsRepository.findById(id);
        if (!itemToRemove.isPresent()) {
            return null;
        }
        Products removedItem = itemToRemove.get();
        productsRepository.delete(removedItem);
        return removedItem;
    }

    public Products updateProduct(Integer id, Products product) {
        Optional<Products> itemToUpdate = productsRepository.findById(id);
        if (!itemToUpdate.isPresent()) {
            return null;
        }
        Products updatedItem = itemToUpdate.get();
        updatedItem.setProductName(product.getProductName());
        productsRepository.save(updatedItem);
        return itemToUpdate.get();
    }

    public String clearProduct() {
        productsRepository.deleteAll();
        return "All item removed";
    }
}

