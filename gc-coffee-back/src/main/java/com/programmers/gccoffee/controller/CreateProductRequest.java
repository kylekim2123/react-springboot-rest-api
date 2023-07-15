package com.programmers.gccoffee.controller;

import com.programmers.gccoffee.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateProductRequest {

    public String productName;
    public Category category;
    public long price;
    public String description;
}
