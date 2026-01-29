package com.amalitech.smartEcommerce.domain.user.service;

import com.amalitech.smartEcommerce.domain.user.entity.AppUser;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface AppUserService {
    AppUser create(AppUser user);
    AppUser getById(UUID id);
    Page<AppUser> listPaged(int page, int size);
    void delete(UUID id);
}

