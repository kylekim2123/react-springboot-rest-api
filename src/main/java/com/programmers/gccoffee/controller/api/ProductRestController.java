package com.programmers.gccoffee.controller.api;

import com.programmers.gccoffee.model.Category;
import com.programmers.gccoffee.model.Product;
import com.programmers.gccoffee.service.ProductService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("/api/v1/products")
    public List<Product> productList(@RequestParam Optional<Category> category) {
        return category.map(productService::getProductsByCategory)
                .orElse(productService.getAllProducts());
    }
}
