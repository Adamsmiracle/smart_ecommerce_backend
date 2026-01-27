package com.amalitech.smartEcommerce.domain.cart.entity;

import com.amalitech.smartEcommerce.common.BaseEntity;
import com.amalitech.smartEcommerce.domain.product.entity.ProductItem;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "shopping_cart_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ShoppingCartItem extends BaseEntity {

    @NotNull(message = "Shopping cart item must belong to a cart")
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private ShoppingCart cart;

    @NotNull(message = "There must be an item in the shopping cart item")
    @ManyToOne
    @JoinColumn(name = "product_item_id", nullable = false)
    private ProductItem productItem;

    @Size(min = 1, message = "Quantity must be one or more")
    @Column(name = "quantity")
    private Integer quantity;

}

