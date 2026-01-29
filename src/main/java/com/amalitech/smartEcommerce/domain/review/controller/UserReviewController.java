package com.amalitech.smartEcommerce.domain.review.controller;

import com.amalitech.smartEcommerce.common.dto.PageResponse;
import com.amalitech.smartEcommerce.common.response.ApiResponse;
import com.amalitech.smartEcommerce.domain.review.entity.UserReview;
import com.amalitech.smartEcommerce.domain.review.service.UserReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reviews")
@Tag(name = "Reviews", description = "Product reviews")
public class UserReviewController {

    private final UserReviewService service;

    public UserReviewController(UserReviewService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "create review")
    public ResponseEntity<ApiResponse<UserReview>> create(@RequestBody UserReview r) {
        UserReview created = service.create(r);
        return ResponseEntity.ok(ApiResponse.success("Created", created));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get review by review_id")
    public ResponseEntity<ApiResponse<UserReview>> getById(@PathVariable("id") java.util.UUID id) {
        UserReview r = service.getById(id);
        if (r == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ApiResponse.success(r));
    }

    @GetMapping
    @Operation(summary = "List all reviews")
    public ResponseEntity<ApiResponse<List<UserReview>>> list(@RequestParam(defaultValue = "10") int limit,
                                                               @RequestParam(defaultValue = "0") int offset) {
        return ResponseEntity.ok(ApiResponse.success(service.list(limit, offset)));
    }

    @GetMapping("/by-product/{productId}")
    @Operation(summary = "List reviews by product (paged)")
    public ResponseEntity<ApiResponse<PageResponse<UserReview>>> byProduct(
            @PathVariable UUID productId,
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size
    ) {
        Page<UserReview> reviewPage = service.findByProductId(productId, page, size);
        PageResponse<UserReview> response = PageResponse.from(reviewPage, r -> r);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
