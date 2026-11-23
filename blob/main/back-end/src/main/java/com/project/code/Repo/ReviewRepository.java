package com.project.code.Repo;

import com.project.code.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCustomer_Id(Long customerId);
}
