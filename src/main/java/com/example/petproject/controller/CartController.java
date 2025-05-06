package com.example.petproject.controller;

import com.example.petproject.constant.URLConstant;
import com.example.petproject.model.Cart;
import com.example.petproject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    private final CartService cartService;
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(URLConstant.API_CART)
    public List<Cart> getAllCartItems() {
        return cartService.getAllCartItems();
    }

    @PostMapping(URLConstant.API_CART_ADD)
    public ResponseEntity<String> addToCart(@RequestBody Cart cart) {
        cartService.addToCart(cart);
        return ResponseEntity.ok("Đã thêm sản phẩm vào giỏ hàng");
    }

    @DeleteMapping(URLConstant.API_CART_DELETE)
    public ResponseEntity<String> deleteCartItem(@RequestParam int id) {
        cartService.deleteCartItem(id);
        return ResponseEntity.ok("Đã xóa sản phẩm khỏi giỏ hàng");
    }
}
