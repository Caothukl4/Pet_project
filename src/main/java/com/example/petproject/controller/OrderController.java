package com.example.petproject.controller;

import com.example.petproject.constant.URLConstant;
import com.example.petproject.dto.request.OrderRequest;
import com.example.petproject.dto.respone.OrderResponse;
import com.example.petproject.entity.OrderStatus;
import com.example.petproject.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(URLConstant.API_CREATE_ORDER)
    public ResponseEntity<OrderResponse> createOrderFromCart(@Valid  @PathVariable Long userId,
                                                     @RequestBody OrderRequest request) {
        OrderResponse orderResponse = orderService.createOrderFromCart(userId, request);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping(URLConstant.API_GET_ORDER_ID)
    public ResponseEntity<OrderResponse> getOrderId(@Valid @RequestParam Long orderId) {
        OrderResponse orderResponse = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping(URLConstant.API_GET_ORDER_USERID)
    public ResponseEntity<List<OrderResponse>> getUserOrders(@Valid @RequestParam Long userId) {
        List<OrderResponse> orders = orderService.getUserOrders(userId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping(URLConstant.API_UPDATE_ORDER_STATUS)
    public ResponseEntity<OrderResponse> updateOrderStatus(@Valid @PathVariable Long orderId,
                                                           @RequestParam String status) {
        try {
            OrderResponse updatedOrder = orderService.updateOrderStatus(orderId, status);
            return ResponseEntity.ok(updatedOrder);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping(URLConstant.API_DELETE_ORDER)
    public ResponseEntity<Void> deleteOrder(@Valid @RequestParam Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/statuses")
//    public ResponseEntity<List<String>> getAvailableStatuses() {
//        List<String> statuses = Arrays.stream(OrderStatus.values())
//                .map(Enum::name)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(statuses);
//    }

}
