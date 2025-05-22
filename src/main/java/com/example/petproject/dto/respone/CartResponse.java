package com.example.petproject.dto.respone;

import java.math.BigDecimal;
import java.util.List;

public class CartResponse {
    private Long id;
    private List<CartItemResponse> items;
    private BigDecimal total;
    private Long userId;

    // Constructors, getters, setters
    public CartResponse() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItemResponse> getItems() {
        return items;
    }

    public void setItems(List<CartItemResponse> items) {
        this.items = items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
