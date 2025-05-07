package com.lauder.app.ecommapp.repo;


import com.lauder.app.ecommapp.dto.request.payments.PaymentRequest;
import com.lauder.app.ecommapp.dto.response.payments.PaymentResponse;

public interface PaymentStrategy {
    PaymentResponse processPayment(PaymentRequest request);
}


