package com.lauder.app.ecommapp.repo;

import com.lauder.app.ecommapp.model.Shipping;
import com.lauder.app.ecommapp.model.ShippingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface IShippingRepo extends JpaRepository<Shipping, Long> {


    @Query("SELECT s FROM Shipping s WHERE s.shippingStatuses IN :statuses")
    Optional<Shipping> findByShippingStatuses(@Param("status") ShippingStatus shippingStatuses);

    @Query("SELECT s FROM Shipping s WHERE s.updatedAt = (SELECT MAX(s2.updatedAt) FROM ShippingStatus s2 WHERE s2.shipping.shippingStatuses = s.shippingStatuses)")
    Set<ShippingStatus> findLatestStatusesPerStatusType();


    @Query("SELECT s FROM ShippingStatus s WHERE s.updatedAt = (SELECT MAX(s2.updatedAt) FROM ShippingStatus s2 WHERE s2.shipping = s.shipping) AND s.shipping.order.orderId = :orderId")
    Set<Shipping> findLatestStatusesFromOrderId(@Param("orderId") Long orderId);

    @Query("SELECT s FROM ShippingStatus s WHERE s.updatedAt = (SELECT MAX(s2.updatedAt) FROM ShippingStatus s2 WHERE s2.shipping = s.shipping) AND s.shipping.customers.id = :customerId")
    Set<Shipping> findLatestStatusesFromCustomerId(@Param("customerId") Long customerId);
}