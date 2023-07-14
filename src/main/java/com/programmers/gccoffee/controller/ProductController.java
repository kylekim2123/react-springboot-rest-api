package com.programmers.gccoffee.controller;

import com.programmers.gccoffee.model.Product;
import com.programmers.gccoffee.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public String productPage(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "product-list";
    }
}
