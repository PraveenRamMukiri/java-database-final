package com.project.code.Controller;

import com.project.code.model.Customer;
import com.project.code.model.Review;
import com.project.code.Repo.CustomerRepository;
import com.project.code.Repo.ReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;

    public ReviewController(ReviewRepository reviewRepository,
                            CustomerRepository customerRepository) {
        this.reviewRepository = reviewRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        return reviewRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getReviewsByCustomer(@PathVariable Long customerId) {
        return customerRepository.findById(customerId)
                .<ResponseEntity<?>>map(customer -> {
                    List<Review> reviews =
                            reviewRepository.findByCustomer_Id(customerId);
                    return ResponseEntity.ok(Map.of(
                            "customerName", customer.getName(),
                            "reviews", reviews));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
