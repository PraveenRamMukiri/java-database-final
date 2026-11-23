package com.project.code.Repo;

import com.project.code.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProduct_IdAndStore_Id(Long productId, Long storeId);
    List<Inventory> findByStore_Id(Long storeId);
    List<Inventory> findByProduct_Id(Long productId);
    void deleteByProduct_Id(Long productId);
}
