package com.programmers.gccoffee.controller.api;

import com.programmers.gccoffee.controller.CreateOrderRequest;
import com.programmers.gccoffee.model.Email;
import com.programmers.gccoffee.model.Order;
import com.programmers.gccoffee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @PostMapping("/api/v1/orders")
    public Order createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        return orderService.createOrder(
                new Email(createOrderRequest.getEmail()),
                createOrderRequest.getAddress(),
                createOrderRequest.getPostcode(),
                createOrderRequest.getOrderItems()
        );
    }
}
