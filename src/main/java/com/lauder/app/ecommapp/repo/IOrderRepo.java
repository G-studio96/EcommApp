package com.lauder.app.ecommapp.repo;


import com.lauder.app.ecommapp.model.CheckoutModel;
import com.lauder.app.ecommapp.model.OrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface IOrderRepo extends JpaRepository<OrderModel, Long> {

    @Override
    List<OrderModel> findAll();

    List<OrderModel> findByPaymentId(CheckoutModel paymentId);

    List<OrderModel> findByTotalAmountBetween(BigDecimal min, BigDecimal max);

    Optional<OrderModel> findByOrderId(Long orderId);

    List<OrderModel> findByCustomerId(Long customerId);

    List<OrderModel> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    Page<OrderModel> findByCustomerId(Long customerId, Pageable pageable);


    long countByCustomerId(Long customerId);


}
