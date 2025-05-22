package com.example.petproject.service.impl;

import com.example.petproject.dto.request.OrderRequest;
import com.example.petproject.dto.respone.OrderResponse;
import com.example.petproject.entity.*;
import com.example.petproject.exception.APIException;
import com.example.petproject.repository.*;
import com.example.petproject.service.OrderService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

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
    @Transactional
    public OrderResponse createOrderFromCart(Long userId, OrderRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new APIException("User not found with ID: " + userId, 404));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new APIException("Cart not found for user ID: " + userId, 404));

        List<CartItem> cartItems = cart.getItems();
        if (cartItems == null || cartItems.isEmpty()) {
            throw new APIException("Cart is empty. Cannot create order.", 400);
        }

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.valueOf("ORDER_INIT"));
        order.setDescription(request.getDescription());

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItem> orderItemList = new java.util.ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());

            BigDecimal itemTotal = cartItem.getProduct().getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            totalAmount = totalAmount.add(itemTotal);

            orderItemList.add(orderItem);
        }

        order.setTotalAmount(totalAmount);
        order.setOrderItems(orderItemList);

        Order savedOrder = orderRepository.save(order);

        cartItemRepository.deleteByCart(cart);

        return modelMapper.map(savedOrder, OrderResponse.class);
    }


    @Override
    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new APIException("Order not found with ID: " + orderId, 404));
        return modelMapper.map(order, OrderResponse.class);
    }

    @Override
    public List<OrderResponse> getUserOrders(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(order -> modelMapper.map(order, OrderResponse.class)).collect(Collectors.toList());
    }

    @Override
    public OrderResponse updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new APIException("Order not found with ID: " + orderId, 404));

        try {
            OrderStatus newStatus = OrderStatus.valueOf(status.toUpperCase());
            order.setStatus(newStatus);
        } catch (IllegalArgumentException e) {
            throw new APIException("Invalid status: " + status, 400);
        }

        return modelMapper.map(orderRepository.save(order), OrderResponse.class);
    }


    @Override
    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new APIException("Order not found", 404);
        }
        orderRepository.deleteById(orderId);
    }
}
