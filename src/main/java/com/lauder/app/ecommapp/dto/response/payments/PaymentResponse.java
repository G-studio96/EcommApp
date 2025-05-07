package com.lauder.app.ecommapp.dto.response.payments;

import com.lauder.app.ecommapp.model.UsersModel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentResponse {

    private Long paymentId;

    private UsersModel name;

    private String email;

    private String cardDetails;

    private String expiryDate;

    private String cvc;

    private BigDecimal Total;

    private LocalDateTime time;

    private String status;

    private Boolean success;

    private  String message;

    public static String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) {

            return "****";
        }
        String lastFourDigits = cardNumber.replaceAll("^[0-9]", "");

        lastFourDigits = lastFourDigits.substring(Math.max(0, lastFourDigits.length() - 4));

        return  "**** **** **** " + lastFourDigits;
    }

    public Long getPaymentID() {
        return paymentId;
    }

    public Long setPaymentID(Long paymentId) {
        return this.paymentId = paymentId;

    }

    public UsersModel getName() {
        return name;
    }

    public UsersModel setName(UsersModel name) {
        return this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail(String email) {

        return this.email = email;
    }

    public String getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(String cardDetails) {
        this.cardDetails = cardDetails;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal total) {
        Total = total;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
