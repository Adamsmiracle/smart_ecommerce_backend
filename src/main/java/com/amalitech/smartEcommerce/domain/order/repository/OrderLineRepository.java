package com.amalitech.smartEcommerce.domain.order.repository;

import com.amalitech.smartEcommerce.domain.order.entity.OrderLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderLineRepository extends JpaRepository<OrderLine, UUID> {
    List<OrderLine> findByOrder_Id(UUID orderId);

    Page<OrderLine> findByOrder_Id(UUID orderId, Pageable pageable);
}
