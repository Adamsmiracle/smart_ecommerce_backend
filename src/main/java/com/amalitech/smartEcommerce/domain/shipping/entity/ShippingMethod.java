package com.amalitech.smartEcommerce.domain.shipping.entity;

import com.amalitech.smartEcommerce.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Table(name = "shipping_method")
public class ShippingMethod extends BaseEntity {

    @NotNull(message = "name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "price is required")
    @Min(1)
    @Column(name = "price", nullable = false)
    private Double price;

}

