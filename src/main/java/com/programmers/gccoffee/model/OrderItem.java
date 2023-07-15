package com.programmers.gccoffee.model;

import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OrderItem {

    private final UUID productId;
    private final Category category;
    private final long price;
    private final int quantity;
}
