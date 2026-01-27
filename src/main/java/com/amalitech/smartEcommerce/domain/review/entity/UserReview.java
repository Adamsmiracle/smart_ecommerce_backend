package com.amalitech.smartEcommerce.domain.review.entity;

import com.amalitech.smartEcommerce.common.BaseEntity;
import com.amalitech.smartEcommerce.domain.user.entity.AppUser;
import com.amalitech.smartEcommerce.domain.order.entity.OrderLine;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "user_review",
uniqueConstraints = {@UniqueConstraint(name = "uk_review_user_orderline",
columnNames = {"user_id", "ordered_product_id"})})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserReview extends BaseEntity {

    @NotNull(message = "Ordered product is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @NotNull(message = "Ordered product is required")
    @ManyToOne
    @JoinColumn(name = "ordered_product_id", nullable = false)
    private OrderLine orderedProduct;


    @NotNull(message = "Rating value is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot exceed 5")
    @Column(name = "rating_value", nullable = false)
    private Integer ratingValue;

    @Size(max = 1000, message = "Comment cannot exceed 1000 characters")
    @Column(name = "comment", length = 1000)
    private String comment;

}
