package com.example.petproject.controller;

import com.example.petproject.constant.URLConstant;
import com.example.petproject.dto.request.AddToCartRequest;
import com.example.petproject.dto.request.CartItemRequest;
import com.example.petproject.dto.respone.CartItemResponse;
import com.example.petproject.dto.respone.CartResponse;
import com.example.petproject.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping(URLConstant.API_CART_USER)
    public ResponseEntity<CartResponse> getCart(@Valid @RequestParam Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @PostMapping(URLConstant.API_CART_ADD)
    public ResponseEntity<CartItemResponse> addToCart(@Valid @PathVariable Long userId,
                                                      @RequestBody CartItemRequest request) {
        return ResponseEntity.ok(cartService.addToCart(userId, request));
    }

    @PutMapping(URLConstant.API_CART_UPDATE)
    public ResponseEntity<CartItemResponse> updateCartItem(@Valid @PathVariable Long userId,
                                                           @PathVariable Long cartItemId,
                                                           @RequestParam Integer quantity) {
        return ResponseEntity.ok(cartService.updateCartItem(userId, cartItemId, quantity));
    }

    @DeleteMapping(URLConstant.API_CART_DELETE)
    public ResponseEntity<Void> removeFromCart(@Valid @PathVariable Long userId,
                                               @PathVariable Long cartItemId) {
        cartService.removeFromCart(userId, cartItemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(URLConstant.API_CART_CLEAR)
    public ResponseEntity<Void> clearCart(@Valid @PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }

}
