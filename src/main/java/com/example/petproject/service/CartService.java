package com.example.petproject.service;

import com.example.petproject.model.Cart;
import java.util.List;

public interface CartService {
    List<Cart> getAllCartItems();
    void addToCart(Cart cart);
    void deleteCartItem(int id);
}
