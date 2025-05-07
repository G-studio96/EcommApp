package com.lauder.app.ecommapp.repo;



import com.lauder.app.ecommapp.model.UsersModel;
import com.lauder.app.ecommapp.model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface IPaymentRepo extends JpaRepository<PaymentModel, Long> {

    @Override
    List<PaymentModel> findAll();

    List<PaymentModel> findByCardDetails(String cardDetails);
    List<PaymentModel> findByExpiryDate(String expiry);

    List<PaymentModel> findByCvc(String cvc);

    List<PaymentModel> findByTotalBetween(BigDecimal minTotal, BigDecimal maxTotal);

    List<PaymentModel> findByEmail(String email);

    Optional<PaymentModel> findByPaymentId(Long paymentId);

    Optional<PaymentModel> findByName(UsersModel name);

    @Query("SELECT COALESCE(SUM(p.total), 0) FROM PaymentModel p")
    BigDecimal sumAllPayment();

    @Query("SELECT COALESCE(SUM(p.total), 0) FROM PaymentModel p WHERE p.customers.id = :id")
    BigDecimal sumPaymentsByCustomerId(Long customerId);
}
