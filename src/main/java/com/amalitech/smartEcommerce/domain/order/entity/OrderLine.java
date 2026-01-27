package com.amalitech.smartEcommerce.domain.order.entity;

import com.amalitech.smartEcommerce.common.BaseEntity;
import com.amalitech.smartEcommerce.domain.product.entity.ProductItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "order_line", indexes = {
        @Index(name = "idx_orderline_product_item", columnList = "product_item_id"),
        @Index(name = "idx_orderline_order", columnList = "order_id")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class OrderLine extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_item_id", nullable = false)
    private ProductItem productItem;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private CustomerOrder order;

    @NotNull
    @Min(1)
    @Column(name = "qty", nullable = false)
    private Integer qty;

    @Column(name = "price")
    @DecimalMin(value = "0.01", message = "Price must be at least 0.01")
    @Positive(message = "Price must be greater than 0")
    private Double price;
}
