package com.lauder.app.ecommapp.mapper.paymentsMapper;



import com.lauder.app.ecommapp.dto.request.payments.PaymentRequest;
import com.lauder.app.ecommapp.dto.response.payments.PaymentResponse;
import com.lauder.app.ecommapp.model.PaymentModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentMapper {

    public PaymentModel toEntity(PaymentRequest paymentRequest) {

        if(paymentRequest ==null) {
            return null;
        }

        PaymentModel entity = new PaymentModel();
        entity.setName(paymentRequest.getName());
        entity.setEmail(paymentRequest.getEmail());

        entity.setCardDetails(paymentRequest.getCardDetails());
        entity.setExpiryDate(paymentRequest.getExpiryDate());
        entity.setCvc(paymentRequest.getCvc());
        entity.setTotal(paymentRequest.getTotal());
        entity.setPaymentDate(LocalDateTime.now());
        entity.setStatus(PaymentModel.PaymentStatus.PENDING);

        return entity;
    }

    public PaymentResponse toResponse(PaymentModel model) {

        if (model == null) {
            return null;
        }

        PaymentResponse response = new PaymentResponse();

        response.setPaymentID(model.getPaymentID());
        response.setName(model.getName());
        response.setEmail(model.getEmail());

        response.setCardDetails(PaymentResponse.maskCardNumber(model.getCardDetails()));

        response.setExpiryDate(model.getExpiryDate());
        response.setCvc(model.getCvc());
        response.setTime(LocalDateTime.now());
        response.setStatus("COMPLETED");

        return response;
    }


}
