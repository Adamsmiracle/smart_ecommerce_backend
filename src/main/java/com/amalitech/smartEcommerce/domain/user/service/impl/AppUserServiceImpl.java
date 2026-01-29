package com.amalitech.smartEcommerce.domain.user.service.impl;

import com.amalitech.smartEcommerce.domain.payment.repository.UsePaymentMethodRepository;
import com.amalitech.smartEcommerce.domain.user.entity.AppUser;
import com.amalitech.smartEcommerce.domain.user.repository.AppUserRepository;
import com.amalitech.smartEcommerce.domain.user.service.AppUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository repository;
    private final UsePaymentMethodRepository usePaymentMethodRepository;

    public AppUserServiceImpl(AppUserRepository repository,
                              UsePaymentMethodRepository usePaymentMethodRepository) {
        this.repository = repository;
        this.usePaymentMethodRepository = usePaymentMethodRepository;
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
    public Page<AppUser> listPaged(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        AppUser user = repository.findById(id).orElse(null);
        if (user == null) return; // nothing to delete

        // First remove dependent payment method rows to avoid FK violation
        usePaymentMethodRepository.deleteByUser(user);

        // Then delete the user
        repository.delete(user);
    }
}

