package com.amalitech.smartEcommerce.domain.order.entity;

import com.amalitech.smartEcommerce.common.BaseEntity;
import com.amalitech.smartEcommerce.domain.shipping.entity.ShippingMethod;
import com.amalitech.smartEcommerce.domain.user.entity.AppUser;
import com.amalitech.smartEcommerce.domain.payment.entity.UsePaymentMethod;
import com.amalitech.smartEcommerce.domain.location.entity.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.Builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer_order", indexes = {
        @Index(name = "idx_customerorder_user", columnList = "user_id"),
        @Index(name = "idx_customerorder_date", columnList = "order_date")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CustomerOrder extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @NotNull(message = "Order date cannot be empty")
    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    private UsePaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_address_id")
    private Address shippingAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_method_id")
    private ShippingMethod shippingMethod;

    @NotNull
    @Column(name = "order_total", nullable = false)
    private Double orderTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_status")
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<OrderLine> orderLines = new ArrayList<>();

    public void addOrderLine(OrderLine line) {
        if (line == null) return;
        line.setOrder(this);
        this.orderLines.add(line);
    }

    public void removeOrderLine(OrderLine line) {
        if (line == null) return;
        line.setOrder(null);
        this.orderLines.remove(line);
    }
}
