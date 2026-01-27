package com.amalitech.smartEcommerce.domain.product.entity;

import com.amalitech.smartEcommerce.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "product_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ProductItem extends BaseEntity {

    @NotNull(message = "Product is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull(message = "Quantity in stock is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    @Column(name = "qty_in_stock", nullable = false)
    private Integer qtyInStock = 0;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    @Digits(integer = 15, fraction = 4, message = "Price must have max 15 integer and 4 decimal digits")
    @Column(name = "price", nullable = false, precision = 19, scale = 4)
    private BigDecimal price;

    @Size(max = 500, message = "Image URL cannot exceed 500 characters")
    @Pattern(regexp = "^(https?://.*\\.(?:png|jpg|jpeg|gif|webp))?$",
            message = "Must be a valid image URL (png, jpg, jpeg, gif, webp) or empty")
    @Column(name = "image", length = 500)
    private String image;

    @NotBlank(message = "SKU is required")
    @Size(min = 3, max = 50, message = "SKU must be 3-50 characters")
    @Pattern(regexp = "^[A-Z0-9\\-]+$", message = "SKU can only contain uppercase letters, numbers, and hyphens")
    @Column(name = "sku", unique = true, nullable = false, length = 50)
    private String sku;

    @Version
    private Long version;

}
