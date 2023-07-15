package com.programmers.gccoffee.controller;

import com.programmers.gccoffee.model.OrderItem;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateOrderRequest {

    private String email;
    private String address;
    private String postcode;
    private List<OrderItem> orderItems;
}
