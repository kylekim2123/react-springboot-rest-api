package com.programmers.gccoffee.service;

import com.programmers.gccoffee.model.Category;
import com.programmers.gccoffee.model.Product;
import java.util.List;

public interface ProductService {

    Product createProduct(String productName, Category category, long price);

    Product createProduct(String productName, Category category, long price, String description);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(Category category);
}
