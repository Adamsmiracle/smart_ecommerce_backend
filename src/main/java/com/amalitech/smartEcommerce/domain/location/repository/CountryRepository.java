package com.amalitech.smartEcommerce.domain.location.repository;

import com.amalitech.smartEcommerce.domain.location.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {
}

