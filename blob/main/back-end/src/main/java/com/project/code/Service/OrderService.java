package com.project.code.Service;

import com.project.code.model.Inventory;
import com.project.code.model.OrderDetails;
import com.project.code.Repo.InventoryRepository;
import com.project.code.Repo.OrderDetailsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final OrderDetailsRepository orderRepository;
    private final InventoryRepository inventoryRepository;

    public OrderService(OrderDetailsRepository orderRepository,
                        InventoryRepository inventoryRepository) {
        this.orderRepository = orderRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public OrderDetails placeOrder(OrderDetails order) {
        if (order.getProduct() == null || order.getStore() == null) {
            throw new IllegalArgumentException("Product and store are required");
        }
        if (order.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        Long productId = order.getProduct().getId();
        Long storeId = order.getStore().getId();

        Inventory inventory = inventoryRepository
                .findByProduct_IdAndStore_Id(productId, storeId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found"));

        if (inventory.getQuantity() < order.getQuantity()) {
            throw new IllegalArgumentException("Insufficient stock");
        }

        inventory.setQuantity(inventory.getQuantity() - order.getQuantity());
        inventoryRepository.save(inventory);

        return orderRepository.save(order);
    }
}
