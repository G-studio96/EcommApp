package com.lauder.app.ecommapp.model;

import com.lauder.app.ecommapp.converter.EncryptionConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payments")
public class PaymentModel {

    @Id
    @Column(name = "PAYMENT_ID")
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private UsersModel customers;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private OrderModel orderId;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private CategoriesModel category;


    @ManyToOne
    @JoinColumn(name = "NAME")
    private UsersModel name;

    @Email
    @Column(name = "EMAIL")
    private String email;

    @Convert(converter = EncryptionConverter.class)
    @Column(name = "CARD_DETAILS")
    private String cardDetails;

    @Column(name = "CARD_TYPE")
    private String cardType;

    @Column(name = "PAYMENT_TOKEN")
    private String paymentToken;

    @Convert(converter = EncryptionConverter.class)
    @Column(name = "EXPIRY_DATE")
    private String expiryDate;

    @Convert(converter = EncryptionConverter.class)
    @Column(name = "CVC" )
    private String cvc;

    @Column(name = "TOTAL", precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "PAYMENT_DATE")
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_STATUS")
    private PaymentStatus status;

    @Column(name = "PROMO_CODE_APPLIED")
    private String promoCodeApplied;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @Column(name = "LAST_MODIFIED_DATE")
    private LocalDateTime lastModifiedDate;



    public enum PaymentStatus {
        PENDING,
        COMPLETED,
        FAILED,
        REFUNDED
    }

    public UsersModel getName() {
        return name;
    }

    public void setName(UsersModel name) {
        this.name = name;

    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Long getPaymentID() {
        return paymentId;
    }

    public void setPaymentID(Long paymentId) {
        this.paymentId = paymentId;
    }

    public OrderModel getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderModel orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public @Email String setEmail(String email) {
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
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public UsersModel getCustomers() {
        return customers;
    }

    public void setCustomers(UsersModel customers) {
        this.customers = customers;
    }

    public CategoriesModel getCategory() {
        return category;
    }

    public void setCategory(CategoriesModel category) {
        this.category = category;
    }

    public String getPromoCodeApplied() {
        return promoCodeApplied;
    }

    public void setPromoCodeApplied(String promoCodeApplied) {
        this.promoCodeApplied = promoCodeApplied;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
