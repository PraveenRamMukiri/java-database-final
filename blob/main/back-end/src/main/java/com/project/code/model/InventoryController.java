package com.project.code.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final List<Map<String, Object>> inventory = new ArrayList<>();

    @GetMapping
    public List<Map<String, Object>> getAllInventory() {
        return inventory;
    }

    @PostMapping
    public Map<String, Object> addInventory(
            @RequestBody Map<String, Object> item) {
        inventory.add(item);
        return item;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getInventoryItem(@PathVariable int id) {
        if (id < 0 || id >= inventory.size()) {
            throw new NoSuchElementException("Inventory item not found");
        }
        return inventory.get(id);
    }

    @DeleteMapping("/{id}")
    public String deleteInventoryItem(@PathVariable int id) {
        if (id < 0 || id >= inventory.size()) {
            throw new NoSuchElementException("Inventory item not found");
        }
        inventory.remove(id);
        return "Inventory item deleted successfully";
    }
}
