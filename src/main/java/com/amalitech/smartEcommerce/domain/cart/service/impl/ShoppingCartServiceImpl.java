package com.amalitech.smartEcommerce.domain.cart.service.impl;

import com.amalitech.smartEcommerce.domain.cart.entity.ShoppingCart;
import com.amalitech.smartEcommerce.domain.cart.repository.ShoppingCartRepository;
import com.amalitech.smartEcommerce.domain.cart.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository repository;

    public ShoppingCartServiceImpl(ShoppingCartRepository repository) {
        this.repository = repository;
    }

    @Override
    public ShoppingCart create(ShoppingCart cart) {
        return repository.save(cart);
    }

    @Override
    public ShoppingCart getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ShoppingCart> list(int limit, int offset) {
        return repository.findAll();
    }

    @Override
    public ShoppingCart update(ShoppingCart cart) {
        return repository.save(cart);
    }
}

