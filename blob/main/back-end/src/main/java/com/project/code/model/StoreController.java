package com.project.code.controller;

import com.project.code.model.OrderDetails;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class StoreController {

    private final List<OrderDetails> orders = new ArrayList<>();

    @GetMapping
    public List<OrderDetails> getAllOrders() {
        return orders;
    }

    @PostMapping
    public OrderDetails addOrder(@RequestBody OrderDetails order) {
        orders.add(order);
        return order;
    }

    @GetMapping("/{id}")
    public OrderDetails getOrder(@PathVariable int id) {
        if (id < 0 || id >= orders.size()) {
            throw new NoSuchElementException("Order not found");
        }
        return orders.get(id);
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable int id) {
        if (id < 0 || id >= orders.size()) {
            throw new NoSuchElementException("Order not found");
        }
        orders.remove(id);
        return "Order deleted successfully";
    }
}
