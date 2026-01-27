package com.amalitech.smartEcommerce.domain.user.service.impl;

import com.amalitech.smartEcommerce.domain.user.entity.AppUser;
import com.amalitech.smartEcommerce.domain.user.repository.AppUserRepository;
import com.amalitech.smartEcommerce.domain.user.service.AppUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository repository;

    public AppUserServiceImpl(AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public AppUser create(AppUser user) {
        return repository.save(user);
    }

    @Override
    public AppUser getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<AppUser> list(int limit, int offset) {
        return repository.findAll();
    }
}

