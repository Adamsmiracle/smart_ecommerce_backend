package com.amalitech.smartEcommerce.domain.order.repository;

import com.amalitech.smartEcommerce.domain.order.entity.CustomerOrder;
import com.amalitech.smartEcommerce.domain.user.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, UUID> {
    Page<CustomerOrder> findByUser(AppUser user, Pageable pageable);

    List<CustomerOrder> findByUser(AppUser user);

    Page<CustomerOrder> findByOrderStatus_Id(UUID statusId, Pageable pageable);
}
