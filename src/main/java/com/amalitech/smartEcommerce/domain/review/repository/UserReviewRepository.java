package com.amalitech.smartEcommerce.domain.review.repository;

import com.amalitech.smartEcommerce.domain.review.entity.UserReview;
import com.amalitech.smartEcommerce.domain.user.entity.AppUser;
import com.amalitech.smartEcommerce.domain.order.entity.OrderLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserReviewRepository extends JpaRepository<UserReview, UUID> {
    List<UserReview> findByUser(AppUser user);

    Page<UserReview> findByUser(AppUser user, Pageable pageable);

    List<UserReview> findByOrderedProduct(OrderLine orderLine);

    // Find reviews for all order lines that reference product items belonging to a given product
    Page<UserReview> findDistinctByOrderedProduct_ProductItem_Product_Id(UUID productId, Pageable pageable);
}
