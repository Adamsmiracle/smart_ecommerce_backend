package com.amalitech.smartEcommerce.domain.order.repository;

import com.amalitech.smartEcommerce.domain.order.entity.CustomerOrder;
import com.amalitech.smartEcommerce.domain.user.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, UUID> {

    Page<CustomerOrder> findAll(Pageable pageable);
    // By user
    Page<CustomerOrder> findByUser(AppUser user, Pageable pageable);
    List<CustomerOrder> findByUser(AppUser user);

    // convenience: query by user id without loading AppUser
    List<CustomerOrder> findByUser_Id(UUID userId);
    Page<CustomerOrder> findByUser_Id(UUID userId, Pageable pageable);

    // By order status
    Page<CustomerOrder> findByOrderStatus_Id(UUID statusId, Pageable pageable);

    // By date
    Page<CustomerOrder> findByOrderDateBetween(LocalDate start, LocalDate end, Pageable pageable);

    // By order total range
    Page<CustomerOrder> findByOrderTotalBetween(Double minTotal, Double maxTotal, Pageable pageable);

    // By shipping/payment/address associations (by id)
    Page<CustomerOrder> findByShippingMethod_Id(UUID shippingMethodId, Pageable pageable);


}
