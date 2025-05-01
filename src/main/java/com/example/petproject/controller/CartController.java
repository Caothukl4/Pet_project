package com.example.petproject.controller;

import com.example.petproject.constant.URLConstant;
import com.example.petproject.model.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CartController {
    @GetMapping(URLConstant.API_CART)
    public List<Cart> getAllCartItems() {
        List<Cart> cartList = new ArrayList<>();
        cartList.add(new Cart(1, "Sản phẩm A", "Mô tả A", 100000));
        cartList.add(new Cart(2, "Sản phẩm B", "Mô tả B", 200000));
        return cartList;
    }
    @PostMapping(URLConstant.API_CART_ADD)
    public ResponseEntity<String> addToCart(@RequestBody Cart cart) {
        System.out.println("Thêm vào giỏ: " + cart);
        return ResponseEntity.ok("Đã thêm sản phẩm vào giỏ hàng");
    }
    @DeleteMapping(URLConstant.API_CART_DELETE)
    public ResponseEntity<String> deleteCartItem(@RequestParam int id) {
        System.out.println("Xóa sản phẩm khỏi giỏ hàng với id: " + id);
        return ResponseEntity.ok("Đã xóa sản phẩm khỏi giỏ hàng");
    }



}
