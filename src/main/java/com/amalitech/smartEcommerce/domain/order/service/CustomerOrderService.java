package com.amalitech.smartEcommerce.domain.order.service;

import com.amalitech.smartEcommerce.domain.order.dto.OrderRequest;
import com.amalitech.smartEcommerce.domain.order.dto.OrderResponse;
import com.amalitech.smartEcommerce.domain.order.entity.CustomerOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.UUID;

public interface CustomerOrderService {
    CustomerOrder create(CustomerOrder order);
    CustomerOrder getById(UUID id);
    // use pageable style list
    Page<CustomerOrder> list(Pageable pageable);

    CustomerOrder update(UUID id, CustomerOrder updates);
    void delete(UUID id);

    // create from DTO
    OrderResponse createFromRequest(OrderRequest request);

    // queries
    Page<CustomerOrder> findByUserId(UUID userId, Pageable pageable);
    Page<CustomerOrder> findByOrderStatusId(UUID statusId, Pageable pageable);
    Page<CustomerOrder> findByDateRange(LocalDate start, LocalDate end, Pageable pageable);
    Page<CustomerOrder> findByTotalRange(Double minTotal, Double maxTotal, Pageable pageable);
    Page<CustomerOrder> findByShippingMethodId(UUID shippingMethodId, Pageable pageable);
}
