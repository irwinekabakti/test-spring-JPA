package com.example.demo_test_spring_JPA.Cart;
import com.example.demo_test_spring_JPA.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public CustomResponse<Iterable<Cart>> getCart() {
        Iterable<Cart> cart = cartService.getCart();
        return new CustomResponse<>(HttpStatus.OK, "OK", "mantap bang !" , cart);
    }

    @PostMapping
    public CustomResponse<Cart> addItemToCart(@RequestBody Cart cart) {
        Cart newCart = cartService.addCart(cart);
        return new CustomResponse<>(HttpStatus.OK,"OK", "Item added to cart", newCart);
    }

//    @DeleteMapping("/clear")
//    public CustomResponse<String> clearCart() {
//        String result = cartService.clearCart();
//        return new CustomResponse<>(result, 200, null);
//    }

    @DeleteMapping("/{id}")
    public CustomResponse<Cart> removeItem(@PathVariable Integer id) {
        Cart deletedCart = cartService.deleteCart(id);
        if (deletedCart != null) {
            return new CustomResponse<>(HttpStatus.OK,"Ok ! Item removed from cart", "Mantap bang !", deletedCart);
        } else {
            return new CustomResponse<>(HttpStatus.NOT_FOUND,"Ok ! Item not found", "Mantap bang !", null);
        }
    }

    @PutMapping("/{id}")
    public CustomResponse<Cart> updateCart(@PathVariable Integer id, @RequestBody Cart cart) {
        Cart updatedCart = cartService.updateCart(id, cart);
        if (updatedCart != null) {
            return new CustomResponse<>(HttpStatus.OK,"Ok ! Cart updated", "Mantap bang !", updatedCart);
        } else {
            return new CustomResponse<>(HttpStatus.NOT_FOUND,"Ok ! Item not found", "Mantap bang !", null);
        }
    }
}
