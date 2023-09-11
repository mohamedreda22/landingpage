package com.moaaz.modernhome.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/moaaz/api/modernhome/carts")
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{cartId}/product/{productId}")
    public ResponseEntity<?> addToCart(@PathVariable long cartId, @PathVariable long productId) {
        return new ResponseEntity<>(cartService.addToCartWithNewFeature(cartId, productId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{cartId}/product/{productId}")
    public ResponseEntity<?> deleteFromCart(@PathVariable long cartId, @PathVariable long productId) {
        return new ResponseEntity<>(cartService.deleteFromCartWithNewFeature(cartId, productId), HttpStatus.ACCEPTED);
    }

    //TODO Update Cart with Cart Request
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Cart cart) {
        return new ResponseEntity<>(cartService.updateCart(cart), HttpStatus.ACCEPTED);
    }
}
