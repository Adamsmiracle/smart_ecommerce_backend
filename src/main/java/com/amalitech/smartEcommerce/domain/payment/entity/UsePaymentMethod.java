package com.amalitech.smartEcommerce.domain.payment.entity;

import com.amalitech.smartEcommerce.common.BaseEntity;
import com.amalitech.smartEcommerce.domain.user.entity.AppUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "use_payment_method")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UsePaymentMethod extends BaseEntity {

    @NotNull(message = "User is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @NotNull(message = "Payment type is required")
    @ManyToOne
    @JoinColumn(name = "payment_type_id", nullable = false)
    private PaymentType paymentType;

    @NotNull(message = "Payment Provider is required")
    @Column(name = "provider_provider", nullable = false)
    private String provider;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @Column(name = "is_default")
    private Boolean isDefault;

}

