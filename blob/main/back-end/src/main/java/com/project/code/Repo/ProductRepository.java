package com.project.code.Repo;

import com.project.code.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
    List<Product> findByStore_IdAndCategory(Long storeId, String category);
}
