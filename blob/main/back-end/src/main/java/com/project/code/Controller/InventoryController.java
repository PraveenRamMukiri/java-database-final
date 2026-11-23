package com.project.code.Controller;

import com.project.code.model.Inventory;
import com.project.code.Repo.InventoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
public class InventoryController {
    private final InventoryRepository inventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // Use the literal word "null" when a filter should be ignored.
    @GetMapping("/filter/{category}/{name}/{storeId}")
    public List<Inventory> filterInventory(
            @PathVariable String category,
            @PathVariable String name,
            @PathVariable String storeId) {

        return inventoryRepository.findAll().stream()
                .filter(item -> category.equalsIgnoreCase("null")
                        || category.isEmpty()
                        || (item.getProduct().getCategory() != null
                        && item.getProduct().getCategory().toLowerCase(Locale.ROOT)
                        .contains(category.toLowerCase(Locale.ROOT))))
                .filter(item -> name.equalsIgnoreCase("null")
                        || name.isEmpty()
                        || (item.getProduct().getName() != null
                        && item.getProduct().getName().toLowerCase(Locale.ROOT)
                        .contains(name.toLowerCase(Locale.ROOT))))
                .filter(item -> storeId.equalsIgnoreCase("null")
                        || storeId.isEmpty()
                        || item.getStore().getId().equals(Long.valueOf(storeId)))
                .collect(Collectors.toList());
    }

    @GetMapping("/validate/{quantity}/{storeId}/{productId}")
    public ResponseEntity<Boolean> validateQuantity(
            @PathVariable int quantity,
            @PathVariable Long storeId,
            @PathVariable Long productId) {

        if (quantity < 0) return ResponseEntity.badRequest().body(false);

        boolean available = inventoryRepository
                .findByProduct_IdAndStore_Id(productId, storeId)
                .map(stock -> stock.getQuantity() >= quantity)
                .orElse(false);

        return ResponseEntity.ok(available);
    }
}
