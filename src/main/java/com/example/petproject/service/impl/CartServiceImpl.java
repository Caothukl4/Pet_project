package com.example.petproject.service.impl;

import com.example.petproject.model.Cart;
import com.example.petproject.service.CartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final List<Cart> cartList = new ArrayList<>();

    public CartServiceImpl() {
        // Khởi tạo mock data ban đầu
        cartList.add(new Cart(1, "Sản phẩm A", "Mô tả A", 100000));
        cartList.add(new Cart(2, "Sản phẩm B", "Mô tả B", 200000));
    }

    @Override
    public List<Cart> getAllCartItems() {
        return new ArrayList<>(cartList);
    }

    @Override
    public void addToCart(Cart cart) {
        cartList.add(cart);
        System.out.println("Thêm vào giỏ: " + cart);
    }

    @Override
    public void deleteCartItem(int id) {
        cartList.removeIf(cart -> cart.getId() == id);
        System.out.println("Xóa sản phẩm khỏi giỏ hàng với id: " + id);
    }
}
