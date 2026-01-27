package com.amalitech.smartEcommerce.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "product_configuration")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProductConfiguration {

    @EmbeddedId
    private ProductConfigurationId id;

    @MapsId("productItemId")
    @ManyToOne
    @JoinColumn(name = "product_item_id", insertable = false, updatable = false)
    private ProductItem productItem;

    @MapsId("variationOptionId")
    @ManyToOne
    @JoinColumn(name = "variation_option_id", insertable = false, updatable = false)
    private VariationOption variationOption;

    public ProductConfiguration(ProductConfigurationId id) {
        this.id = id;
    }

    public ProductConfigurationId getId() {
        return id;
    }

    public void setId(ProductConfigurationId id) {
        this.id = id;
    }

    public ProductItem getProductItem() {
        return productItem;
    }

    public void setProductItem(ProductItem productItem) {
        this.productItem = productItem;
    }

    public VariationOption getVariationOption() {
        return variationOption;
    }

    public void setVariationOption(VariationOption variationOption) {
        this.variationOption = variationOption;
    }
}
