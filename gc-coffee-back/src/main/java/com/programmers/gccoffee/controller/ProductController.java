package com.programmers.gccoffee.controller;

import com.programmers.gccoffee.model.Product;
import com.programmers.gccoffee.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/new-product")
    public String newProductPage() {
        return "new-product";
    }

    @PostMapping("/products")
    public String insertProduct(CreateProductRequest createProductRequest) {
        productService.createProduct(
                createProductRequest.getProductName(),
                createProductRequest.getCategory(),
                createProductRequest.getPrice(),
                createProductRequest.getDescription());

        return "redirect:/products";
    }
}
