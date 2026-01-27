package com.amalitech.smartEcommerce.domain.user.service;

import com.amalitech.smartEcommerce.domain.user.entity.AppUser;

import java.util.List;
import java.util.UUID;

public interface AppUserService {
    AppUser create(AppUser user);
    AppUser getById(UUID id);
    List<AppUser> list(int limit, int offset);
}

