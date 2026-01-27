package com.amalitech.smartEcommerce.domain.order.service.impl;

import com.amalitech.smartEcommerce.domain.order.entity.CustomerOrder;
import com.amalitech.smartEcommerce.domain.order.repository.CustomerOrderRepository;
import com.amalitech.smartEcommerce.domain.order.service.CustomerOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private final CustomerOrderRepository repository;

    public CustomerOrderServiceImpl(CustomerOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerOrder create(CustomerOrder order) {
        return repository.save(order);
    }

    @Override
    public CustomerOrder getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<CustomerOrder> list(int limit, int offset) {
        return repository.findAll();
    }
}

