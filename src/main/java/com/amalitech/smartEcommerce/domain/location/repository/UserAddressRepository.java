package com.amalitech.smartEcommerce.domain.location.repository;

import com.amalitech.smartEcommerce.domain.location.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserAddressRepository extends JpaRepository<UserAddress, UUID> {
}

