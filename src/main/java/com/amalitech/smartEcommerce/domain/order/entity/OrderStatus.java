package com.amalitech.smartEcommerce.domain.order.entity;

import com.amalitech.smartEcommerce.common.BaseEntity;
import com.amalitech.smartEcommerce.common.enums.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "order_status", uniqueConstraints = {
        @UniqueConstraint(name = "uk_orderstatus_status", columnNames = {"status"})
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class OrderStatus extends BaseEntity {


    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, unique = true)
    private OrderStatusEnum status = OrderStatusEnum.PROCESSING;

}
