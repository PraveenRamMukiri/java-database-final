package com.project.code.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final List<Map<String, Object>> reviews = new ArrayList<>();

    @GetMapping
    public List<Map<String, Object>> getAllReviews() {
        return reviews;
    }

    @PostMapping
    public Map<String, Object> addReview(
            @RequestBody Map<String, Object> review) {
        reviews.add(review);
        return review;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getReview(@PathVariable int id) {
        if (id < 0 || id >= reviews.size()) {
            throw new NoSuchElementException("Review not found");
        }
        return reviews.get(id);
    }

    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable int id) {
        if (id < 0 || id >= reviews.size()) {
            throw new NoSuchElementException("Review not found");
        }
        reviews.remove(id);
        return "Review deleted successfully";
    }
}
