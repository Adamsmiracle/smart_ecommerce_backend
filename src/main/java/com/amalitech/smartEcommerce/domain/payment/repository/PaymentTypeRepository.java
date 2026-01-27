package com.amalitech.smartEcommerce.domain.payment.repository;

import com.amalitech.smartEcommerce.domain.payment.entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, UUID> {
    Optional<PaymentType> findByValueIgnoreCase(String value);
}
