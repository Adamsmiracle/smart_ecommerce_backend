package com.amalitech.smartEcommerce.domain.review.controller;

import com.amalitech.smartEcommerce.common.response.ApiResponse;
import com.amalitech.smartEcommerce.domain.review.entity.UserReview;
import com.amalitech.smartEcommerce.domain.review.service.UserReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class UserReviewController {

    private final UserReviewService service;

    public UserReviewController(UserReviewService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserReview>> create(@RequestBody UserReview r) {
        UserReview created = service.create(r);
        return ResponseEntity.ok(ApiResponse.success("Created", created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserReview>> getById(@PathVariable("id") java.util.UUID id) {
        UserReview r = service.getById(id);
        if (r == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ApiResponse.success(r));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserReview>>> list(@RequestParam(defaultValue = "10") int limit,
                                                               @RequestParam(defaultValue = "0") int offset) {
        return ResponseEntity.ok(ApiResponse.success(service.list(limit, offset)));
    }
}
