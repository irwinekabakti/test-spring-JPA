package com.example.demo_test_spring_JPA.Cart;
import com.example.demo_test_spring_JPA.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<CustomResponse<Iterable<Cart>>> getCart() {
        Iterable<Cart> cart = cartService.getCart();
        CustomResponse<Iterable<Cart>> response = CustomResponse.success(cart, "Cart retrieved successfully!");
        return response.toResponseEntity();
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Cart>> addItemToCart(@RequestBody Cart cart) {
        Cart newCart = cartService.addCart(cart);
        CustomResponse<Cart> response = CustomResponse.success(newCart, "Item added to cart successfully!");
        return response.toResponseEntity();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Cart>> removeItem(@PathVariable Integer id) {
        Cart deletedCart = cartService.deleteCart(id);
        if (deletedCart != null) {
            CustomResponse<Cart> response = CustomResponse.success(deletedCart, "Item removed from cart successfully!");
            return response.toResponseEntity();
        } else {
            CustomResponse<Cart> response = CustomResponse.error(HttpStatus.NOT_FOUND, "Item not found in cart");
            return response.toResponseEntity();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Cart>> updateCart(@PathVariable Integer id, @RequestBody Cart cart) {
        Cart updatedCart = cartService.updateCart(id, cart);
        if (updatedCart != null) {
            CustomResponse<Cart> response = CustomResponse.success(updatedCart, "Cart updated successfully!");
            return response.toResponseEntity();
        } else {
            CustomResponse<Cart> response = CustomResponse.error(HttpStatus.NOT_FOUND, "Item not found in cart");
            return response.toResponseEntity();
        }
    }
}
