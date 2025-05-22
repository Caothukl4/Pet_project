package com.example.petproject.service;

import com.example.petproject.dto.request.AddToCartRequest;
import com.example.petproject.dto.request.CartItemRequest;
import com.example.petproject.dto.respone.CartItemResponse;
import com.example.petproject.dto.respone.CartResponse;

import java.util.List;

public interface CartService {
    CartResponse getCart(Long userId);
    CartItemResponse addToCart(Long userId, CartItemRequest request);
    CartItemResponse updateCartItem(Long userId, Long cartItemId, Integer quantity);
    void removeFromCart(Long userId, Long cartItemId);
    void clearCart(Long userId);
}
