package com.project.code.Service;

import com.project.code.model.Inventory;
import com.project.code.Repo.InventoryRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ServiceClass {
    private final InventoryRepository inventoryRepository;

    public ServiceClass(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public boolean checkInventory(Long productId, Long storeId) {
        return inventoryRepository
                .findByProduct_IdAndStore_Id(productId, storeId)
                .isPresent();
    }

    public Optional<Inventory> getInventory(Long productId, Long storeId) {
        return inventoryRepository
                .findByProduct_IdAndStore_Id(productId, storeId);
    }
}
