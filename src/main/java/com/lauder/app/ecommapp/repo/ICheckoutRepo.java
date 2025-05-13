package com.lauder.app.ecommapp.repo;


import com.lauder.app.ecommapp.model.CheckoutModel;
import com.lauder.app.ecommapp.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ICheckoutRepo extends JpaRepository<CheckoutModel, Long> {

    @Override
    List<CheckoutModel> findAll();

    List<CheckoutModel> findByCardDetails(String cardDetails);

    List<CheckoutModel> findByExpiryDate(String expiry);

    List<CheckoutModel> findByCvc(String cvc);

    List<CheckoutModel> findByTotalBetween(BigDecimal minTotal, BigDecimal maxTotal);

    List<CheckoutModel> findByEmail(String email);

    Optional<CheckoutModel> findByPaymentId(Long paymentId);

    Optional<CheckoutModel> findByName(UsersModel name);

    @Query("SELECT COALESCE(SUM(p.total), 0) FROM CheckoutModel p")
    BigDecimal sumAllPayment();

    @Query("SELECT COALESCE(SUM(p.total), 0) FROM CheckoutModel p WHERE p.customers.id = :id")
    BigDecimal sumPaymentsByCustomerId(Long customerId);
}
