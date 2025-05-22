package com.example.petproject.service.impl;

import com.example.petproject.dto.request.AddToCartRequest;
import com.example.petproject.dto.request.CartItemRequest;
import com.example.petproject.dto.respone.CartItemResponse;
import com.example.petproject.dto.respone.CartResponse;
import com.example.petproject.entity.Cart;
import com.example.petproject.entity.CartItem;
import com.example.petproject.entity.Product;
import com.example.petproject.entity.User;
import com.example.petproject.exception.APIException;
import com.example.petproject.repository.CartItemRepository;
import com.example.petproject.repository.CartRepository;
import com.example.petproject.repository.ProductRepository;
import com.example.petproject.repository.UserRepository;
import com.example.petproject.service.CartService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CartResponse getCart(Long userId) {
        Cart cart = getCartByUserId(userId);

        List<CartItemResponse> items = cart.getItems().stream()
                .map(item -> modelMapper.map(item, CartItemResponse.class))
                .collect(Collectors.toList());
        BigDecimal total = cart.getItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        CartResponse response = new CartResponse();
        response.setId(cart.getId());
        response.setTotal(total);
        response.setUserId(userId);
        response.setItems(items);
        return response;
    }

    @Override
    public CartItemResponse addToCart(Long userId, CartItemRequest request) {
        Cart cart = getCartByUserId(userId);
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new APIException("Sản phẩm không tồn tại", 404));

        // Nếu đã có item cùng productId thì cộng dồn
        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());
        }

        CartItem savedItem = cartItemRepository.save(cartItem);
        return modelMapper.map(savedItem, CartItemResponse.class);
    }

    @Override
    public CartItemResponse updateCartItem(Long userId, Long cartItemId, Integer quantity) {
        Cart cart = getCartByUserId(userId);
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new APIException("Không tìm thấy item trong giỏ", 404));

        if (!cartItem.getCart().getId().equals(cart.getId())) {
            throw new APIException("Item không thuộc về giỏ hàng của người dùng này", 400); // 400 Bad Request
        }

        cartItem.setQuantity(quantity);
        CartItem updatedItem = cartItemRepository.save(cartItem);
        return modelMapper.map(updatedItem, CartItemResponse.class);
    }

    @Override
    public void removeFromCart(Long userId, Long cartItemId) {
        Cart cart = getCartByUserId(userId);
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new APIException("Không tìm thấy item trong giỏ", 404));

        if (!cartItem.getCart().getId().equals(cart.getId())) {
            throw new APIException("Item không thuộc về giỏ hàng của người dùng này", 400);
        }

        cartItemRepository.delete(cartItem);
    }

    @Override
    @Transactional
    public void clearCart(Long userId) {
        Cart cart = getCartByUserId(userId);
        cartItemRepository.deleteByCart(cart); // Xóa các CartItem thuộc cart
    }

    private Cart getCartByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new APIException("Người dùng không tồn tại", 404));

        return cartRepository.findByUser(user)
                .orElseThrow(() -> new APIException("Giỏ hàng không tồn tại", 404));
    }

}
