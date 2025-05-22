package com.example.petproject.service;

import com.example.petproject.dto.request.OrderRequest;
import com.example.petproject.dto.respone.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrderFromCart(Long userId, OrderRequest request);
    OrderResponse getOrderById(Long orderId);
    List<OrderResponse> getUserOrders(Long userId);
    OrderResponse updateOrderStatus(Long orderId, String status);
    void deleteOrder(Long orderId);
}
