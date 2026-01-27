package com.amalitech.smartEcommerce.domain.order.service;

import com.amalitech.smartEcommerce.domain.order.entity.CustomerOrder;

import java.util.List;
import java.util.UUID;

public interface CustomerOrderService {
    CustomerOrder create(CustomerOrder order);
    CustomerOrder getById(UUID id);
    List<CustomerOrder> list(int limit, int offset);
}

