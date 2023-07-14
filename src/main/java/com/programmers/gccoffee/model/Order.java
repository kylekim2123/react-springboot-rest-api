package com.programmers.gccoffee.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Order {

    private final UUID orderId;
    private final Email email;
    private final List<OrderItem> orderItems;
    private final LocalDateTime createdAt;

    private String address;
    private String postcode;
    private OrderStatus orderStatus;
    private LocalDateTime updatedAt;

    public Order(UUID orderId, Email email, List<OrderItem> orderItems, String address, String postcode, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.email = email;
        this.orderItems = orderItems;
        this.address = address;
        this.postcode = postcode;
        this.orderStatus = orderStatus;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void setAddress(String address) {
        this.address = address;
        this.updatedAt = LocalDateTime.now();
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
        this.updatedAt = LocalDateTime.now();
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        this.updatedAt = LocalDateTime.now();
    }
}
