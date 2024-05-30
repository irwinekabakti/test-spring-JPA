package com.example.demo_test_spring_JPA.Cart;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public Iterable<Cart> getCart() {
        return cartService.getCart();
    }

    @PostMapping
    public Cart addItemToCart(@RequestBody Cart cart) {
        return cartService.addCart(cart);
    }

    @DeleteMapping("/clear")
    public String clearCart() {
        return cartService.clearCart();
    }

    @DeleteMapping("/{id}")
    public Cart removeItem(@PathVariable Integer id) {
        return cartService.deleteCart(id);
    }

    @PutMapping("/{id}")
    public Cart putMethodName(@PathVariable Integer id, @RequestBody Cart cart) {
        return cartService.updateCart(id, cart);
    }
}
