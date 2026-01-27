package com.amalitech.smartEcommerce.domain.payment.repository;

import com.amalitech.smartEcommerce.domain.payment.entity.UsePaymentMethod;
import com.amalitech.smartEcommerce.domain.user.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsePaymentMethodRepository extends JpaRepository<UsePaymentMethod, UUID> {
    List<UsePaymentMethod> findByUser(AppUser user);

    Optional<UsePaymentMethod> findByUserAndIsDefaultTrue(AppUser user);
}
