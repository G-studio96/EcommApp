package com.lauder.app.ecommapp.repo;

import com.lauder.app.ecommapp.model.Shipping;
import com.lauder.app.ecommapp.model.ShippingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IShippingRepo extends JpaRepository<Shipping, Long> {


    List<Shipping> findByShippingId(Long shippingId);

    @Query("SELECT s FROM Shipping s WHERE s.shippingStatuses IN :statuses")
    List<Shipping> findByShippingStatuses(@Param("status") Set<ShippingStatus> shippingStatuses);

    @Query(""" 
                SELECT s.shipping FROM ShippingStatus s
                WHERE s.updatedAt = (
                    SELECT MAX(s2.updatedAt) FROM ShippingStatus s2
                )
            """)
    Shipping findShippingWithMostRecentlyUpdatedStatus();

    @Query("""
                SELECT s.shipping FROM ShippingStatus s
                WHERE s.status = 'DELIVERED'
                AND s.updatedAt = (
                    SELECT MAX(s2.updatedAt) FROM ShippingStatus s2 WHERE s2.shipping = s.shipping
                )
            """)
    Shipping findAllCompletedOrders();


    @Query("SELECT s.shipping FROM ShippingStatus s WHERE s.updatedAt = (SELECT MAX(s2.updatedAt) FROM ShippingStatus s2 WHERE s2.shipping = s.shipping) AND s.shipping.order.orderId = :orderId")
    Set<Shipping> findLatestStatusesFromOrderId(@Param("orderId") Long orderId);

    @Query("SELECT s.shipping FROM ShippingStatus s WHERE s.updatedAt = (SELECT MAX(s2.updatedAt) FROM ShippingStatus s2 WHERE s2.shipping = s.shipping) AND s.shipping.customers.id = :customerId")
    Set<Shipping> findLatestStatusesFromCustomerId(@Param("customerId") Long customerId);
}