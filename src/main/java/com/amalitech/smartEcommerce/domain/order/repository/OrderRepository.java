package com.amalitech.smartEcommerce.domain.order.repository;

import com.amalitech.smartEcommerce.domain.order.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<CustomerOrder, UUID> {
}
