package com.amalitech.smartEcommerce.domain.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Embeddable
public class ProductConfigurationId implements Serializable {
    @Column(name = "product_item_id")
    private UUID productItemId;

    @Column(name = "variation_option_id")
    private UUID variationOptionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductConfigurationId that = (ProductConfigurationId) o;
        return Objects.equals(productItemId, that.productItemId) && Objects.equals(variationOptionId, that.variationOptionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productItemId, variationOptionId);
    }
}

