package com.amalitech.smartEcommerce.domain.review.service;

import com.amalitech.smartEcommerce.domain.review.entity.UserReview;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface UserReviewService {
    UserReview create(UserReview review);
    UserReview getById(UUID id);
    List<UserReview> list(int limit, int offset);

    Page<UserReview> findByProductId(UUID productId, int page, int size);
}

