package com.project.code.Controller;

import com.project.code.model.Product;
import com.project.code.Repo.ProductRepository;
import com.project.code.Repo.InventoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    public ProductController(ProductRepository productRepository,
                             InventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        inventoryRepository.deleteByProduct_Id(id);
        productRepository.deleteById(id);
        return ResponseEntity.ok("Product and inventory deleted successfully");
    }
}
