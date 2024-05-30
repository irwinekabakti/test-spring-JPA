package com.example.demo_test_spring_JPA.service;

import java.util.Optional;

import com.example.demo_test_spring_JPA.model.Cart;
import com.example.demo_test_spring_JPA.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public Iterable<Cart> getCart() {
        return this.cartRepository.findAll();
    }

    public String checkCart() {
        if(this.cartRepository.count() > 0) {
            return "Cart is not empty";
        }
        return "Cart is empty";
    }

    public Cart addCart(Cart cart) {
        this.cartRepository.save(cart);
        return cart;
    }

    public Cart deleteCart(Integer id) {
        Optional<Cart> itemToRemove = this.cartRepository.findById(id);
        if(!itemToRemove.isPresent()){
            return null;
        }

        Cart removedItem = itemToRemove.get();
        this.cartRepository.delete(removedItem);
        return removedItem;
    }

    public Cart updateCart(Integer id, Cart cart) {
        Optional<Cart> itemToUpdate = this.cartRepository.findById(id);
        if(!itemToUpdate.isPresent()){
            return null;
        }
        Cart updatedItem = itemToUpdate.get();
        updatedItem.setQuantity(cart.getQuantity());
        updatedItem.setProductId(cart.getProductId());
        this.cartRepository.save(updatedItem);
        return itemToUpdate.get();
    }

    public String clearCart() {
        this.cartRepository.deleteAll();
        return "All item removed";
    }

}
