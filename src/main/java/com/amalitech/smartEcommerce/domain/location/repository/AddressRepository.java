package com.amalitech.smartEcommerce.domain.location.repository;

import com.amalitech.smartEcommerce.domain.location.entity.Address;
import com.amalitech.smartEcommerce.domain.location.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    List<Address> findByCityIgnoreCase(String city);

    List<Address> findByCountry(Country country);
}
