package com.lauder.app.ecommapp.service.paymentsservice;


import com.lauder.app.ecommapp.dto.request.payments.PaymentRequest;
import com.lauder.app.ecommapp.dto.response.payments.PaymentResponse;
import com.lauder.app.ecommapp.repo.PaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class PaymentProcessorService {
    private final Map<String, PaymentStrategy> paymentStrategies;

    private final List<String> availablePaymentMethods = Arrays.asList(
            "credit_card", "paypal", "stripe", "revolut"
    );

    @Autowired
    public PaymentProcessorService(Map<String, PaymentStrategy> paymentStrategies) {
        this.paymentStrategies = paymentStrategies;
    }

    public PaymentResponse paymentResponse(String paymentMethod, PaymentRequest request) {
        PaymentStrategy strategy = paymentStrategies.get(paymentMethod);

        if (strategy == null) {
            throw new UnsupportedOperationException("Payment method not supported: " + paymentMethod);

        }
        return strategy.processPayment(request);
    }


    public List<String> getAvailablePaymentsMethods() {
        return  availablePaymentMethods;
    }
}
