package com.project.code.service;

import com.project.code.model.OrderDetails;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class OrderService {

    private final List<OrderDetails> orders = new ArrayList<>();

    public List<OrderDetails> getAllOrders() {
        return orders;
    }

    public OrderDetails addOrder(OrderDetails order) {
        orders.add(order);
        return order;
    }

    public OrderDetails getOrderById(Long id) {
        return orders.stream()
                .filter(order -> order.getId() != null &&
                        order.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Order not found"));
    }

    public String deleteOrder(Long id) {
        OrderDetails order = getOrderById(id);
        orders.remove(order);
        return "Order deleted successfully";
    }
}
