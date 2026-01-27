package com.amalitech.smartEcommerce.domain.payment.entity;

import com.amalitech.smartEcommerce.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name = "payment_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class PaymentType extends BaseEntity {

    @Column(name = "value", nullable = false)
    private String value;

}

