package com.amalitech.smartEcommerce.domain.product.entity;

import com.amalitech.smartEcommerce.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "variation_option")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class VariationOption extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "variation_id", nullable = false)
    private Variation variation;

    @Column(name = "value")
    private String value;

}
