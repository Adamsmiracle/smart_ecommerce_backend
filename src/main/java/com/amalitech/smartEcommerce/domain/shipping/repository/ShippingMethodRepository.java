package com.amalitech.smartEcommerce.domain.shipping.repository;

import com.amalitech.smartEcommerce.domain.shipping.entity.ShippingMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ShippingMethodRepository extends JpaRepository<ShippingMethod, UUID> {
    List<ShippingMethod> findByNameContainingIgnoreCase(String name);

    Page<ShippingMethod> findByNameContainingIgnoreCase(String name, Pageable pageable);

    List<ShippingMethod> findByPriceLessThan(Double price);
}