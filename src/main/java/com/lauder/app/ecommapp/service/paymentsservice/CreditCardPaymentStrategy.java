package com.lauder.app.ecommapp.service.paymentsservice;


import com.lauder.app.ecommapp.dto.request.payments.PaymentRequest;
import com.lauder.app.ecommapp.dto.response.payments.PaymentResponse;
import com.lauder.app.ecommapp.repo.PaymentStrategy;
import org.springframework.stereotype.Service;

@Service("creditCardPayment")
public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        return null;
    }
    
}
