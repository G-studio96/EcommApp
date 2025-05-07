package com.lauder.app.ecommapp.service;


import com.lauder.app.ecommapp.dto.request.payments.PaymentRequest;
import com.lauder.app.ecommapp.dto.response.payments.PaymentResponse;
import com.lauder.app.ecommapp.mapper.paymentsMapper.PaymentMapper;
import com.lauder.app.ecommapp.model.PaymentModel;
import com.lauder.app.ecommapp.repo.IPaymentRepo;
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
public class PaymentService  {

    private final IPaymentRepo iPaymentRepo;
    private final PaymentMapper paymentMapper;

    final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    public  PaymentService(IPaymentRepo iPaymentRepo, PaymentMapper paymentMapper) {

        this.iPaymentRepo = iPaymentRepo;
        this.paymentMapper = paymentMapper;
    }


    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        logger.info("Creating payment for order: {}", paymentRequest.getOrderId());
        PaymentModel paymentModel = paymentMapper.toEntity(paymentRequest);
        paymentModel.setPaymentDate(LocalDateTime.now());
        paymentModel.setStatus(PaymentModel.PaymentStatus.PENDING);
        PaymentModel savedPayment = iPaymentRepo.save(paymentModel);
        return  paymentMapper.toResponse(savedPayment);

    }

    @Cacheable("payments")
    public PaymentResponse getPayment(Long id) {
        return iPaymentRepo.findById(id)
                .map(paymentMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));

    }

    public PaymentResponse updatePaymentStatus(Long id, PaymentModel.PaymentStatus status) {
        PaymentModel paymentModel = iPaymentRepo.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Payment not found with id: " + id));
            return paymentMapper.toResponse(iPaymentRepo.save(paymentModel));
    }


}
