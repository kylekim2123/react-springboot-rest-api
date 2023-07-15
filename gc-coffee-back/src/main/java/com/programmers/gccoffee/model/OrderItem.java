package com.programmers.gccoffee.model;

import java.util.UUID;
import lombok.Getter;

@Getter
public class OrderItem {

    private UUID productId;
    private Category category;
    private long price;
    private int quantity;
}
