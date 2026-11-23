package com.project.code.Controller;

import com.project.code.model.OrderDetails;
import com.project.code.Repo.StoreRepository;
import com.project.code.Service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StoreController {
    private final StoreRepository storeRepository;
    private final OrderService orderService;

    public StoreController(StoreRepository storeRepository, OrderService orderService) {
        this.storeRepository = storeRepository;
        this.orderService = orderService;
    }

    @GetMapping("/validate/store/{id}")
    public ResponseEntity<Boolean> validateStore(@PathVariable Long id) {
        return ResponseEntity.ok(storeRepository.findById(id).isPresent());
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody OrderDetails order) {
        try {
            return ResponseEntity.ok(orderService.placeOrder(order));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                    .body("Unable to place order: " + ex.getMessage());
        }
    }
}
