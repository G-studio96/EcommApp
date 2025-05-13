package com.lauder.app.ecommapp.dto.response.order;


import com.lauder.app.ecommapp.model.CheckoutModel;
import com.lauder.app.ecommapp.model.OrderStatus;
import com.lauder.app.ecommapp.model.UsersModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class OrderResponse {

    private Long orderId;

    private UsersModel customerId;

    private CheckoutModel payment;

    private Set<OrderStatus> statue;

    private LocalDateTime orderDate;

    private BigDecimal totalAmount;

    private Set<OrderItemResponse> orderItems;




    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public UsersModel getCustomer() {
        return customerId;
    }

    public void setCustomer(UsersModel customerId) {
        this.customerId = customerId;
    }

    public CheckoutModel getPayment() {
        return payment;
    }

    public void setPayment(CheckoutModel payment) {
        this.payment = payment;
    }

    public Set<OrderStatus> getStatue() {
        return statue;
    }

    public void setStatue(Set<OrderStatus> statue) {
        this.statue = statue;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public UsersModel getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UsersModel customerId) {
        this.customerId = customerId;
    }

    public Set<OrderItemResponse> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItemResponse> orderItems) {
        this.orderItems = orderItems;
    }
}
