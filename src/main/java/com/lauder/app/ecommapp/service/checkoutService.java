package com.lauder.app.ecommapp.service;


import com.lauder.app.ecommapp.dto.request.payments.PaymentRequest;
import com.lauder.app.ecommapp.dto.response.payments.PaymentResponse;
import com.lauder.app.ecommapp.mapper.paymentsMapper.PaymentMapper;
import com.lauder.app.ecommapp.model.CheckoutModel;
import com.lauder.app.ecommapp.repo.ICheckoutRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class checkoutService {

    private final ICheckoutRepo iCheckoutRepo;
    private final PaymentMapper paymentMapper;

    final Logger logger = LoggerFactory.getLogger(checkoutService.class);

    @Autowired
    public checkoutService(ICheckoutRepo iCheckoutRepo, PaymentMapper paymentMapper) {

        this.iCheckoutRepo = iCheckoutRepo;
        this.paymentMapper = paymentMapper;
    }


    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        logger.info("Creating payment for order: {}", paymentRequest.getOrderId());
        CheckoutModel paymentModel = paymentMapper.toEntity(paymentRequest);
        paymentModel.setPaymentDate(LocalDateTime.now());
        paymentModel.setStatus(CheckoutModel.PaymentStatus.PENDING);
        CheckoutModel savedPayment = iCheckoutRepo.save(paymentModel);
        return  paymentMapper.toResponse(savedPayment);

    }

    @Cacheable("payments")
    public PaymentResponse getPayment(Long id) {
        return iCheckoutRepo.findById(id)
                .map(paymentMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));

    }

    public PaymentResponse updatePaymentStatus(Long id, CheckoutModel.PaymentStatus status) {
        CheckoutModel paymentModel = iCheckoutRepo.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Payment not found with id: " + id));
        return paymentMapper.toResponse(iCheckoutRepo.save(paymentModel));
    }


}
