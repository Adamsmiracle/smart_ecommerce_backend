package com.amalitech.smartEcommerce.domain.review.service.impl;

import com.amalitech.smartEcommerce.domain.review.entity.UserReview;
import com.amalitech.smartEcommerce.domain.review.repository.UserReviewRepository;
import com.amalitech.smartEcommerce.domain.review.service.UserReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserReviewServiceImpl implements UserReviewService {

    private final UserReviewRepository repository;

    public UserReviewServiceImpl(UserReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserReview create(UserReview review) {
        return repository.save(review);
    }

    @Override
    public UserReview getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<UserReview> list(int limit, int offset) {
        return repository.findAll();
    }

    @Override
    public Page<UserReview> findByProductId(UUID productId, int page, int size) {
        return repository.findDistinctByOrderedProduct_ProductItem_Product_Id(productId, PageRequest.of(page, size));
    }
}

