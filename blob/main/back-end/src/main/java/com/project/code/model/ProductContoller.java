package com.project.code.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final List<Map<String, Object>> products = new ArrayList<>();

    @GetMapping
    public List<Map<String, Object>> getAllProducts() {
        return products;
    }

    @PostMapping
    public Map<String, Object> addProduct(
            @RequestBody Map<String, Object> product) {
        products.add(product);
        return product;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getProduct(@PathVariable int id) {
        if (id < 0 || id >= products.size()) {
            throw new NoSuchElementException("Product not found");
        }
        return products.get(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        if (id < 0 || id >= products.size()) {
            throw new NoSuchElementException("Product not found");
        }
        products.remove(id);
        return "Product deleted successfully";
    }
}
