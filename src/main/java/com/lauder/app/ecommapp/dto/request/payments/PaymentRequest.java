package com.lauder.app.ecommapp.dto.request.payments;


import com.lauder.app.ecommapp.model.UsersModel;
import com.lauder.app.ecommapp.model.OrderModel;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class PaymentRequest {

    @NotBlank(message = "Order Id is required")
    private OrderModel orderId;

    @NotBlank(message = "the name required for payment")
    private UsersModel name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;


    private String promoCode;

    @NotBlank(message = "The card details are required payment")
    @Pattern(regexp = "^[0-9]{4}(-[0-9]{4}){3}$", message = "Card number must be in format XXXX-XXXX-XXXX-XXXX")
    private String cardDetails;

    @NotBlank(message = "CVC is required")
    @Pattern(regexp = "^(0[1-9]|1[0-2])/([0-9]{2})$", message = "Expiry date must be in format MM/YY")
    private String expiryDate;

    @NotBlank(message = "CVC is required")
    @Pattern(regexp = "^[0-9]{3,4}$", message = "CVC must be 3 or 4 digits")
    private String cvc;

    @Positive
    private BigDecimal total;



    public OrderModel getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderModel orderId) {
        this.orderId = orderId;
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

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
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
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
