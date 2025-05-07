package com.lauder.app.ecommapp.dto.request.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class OrderRequest {

    private Long orderId;

    private Long customerId;
    private Long paymentId;
    private Long statusId;
    private Set<OrderStatusRequest> orderStatus;

    private LocalDateTime orderDate;

    private BigDecimal totalAmount;

    private Set<OrderItemRequest> orderItems;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Set<OrderStatusRequest> getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Set<OrderStatusRequest> orderStatus) {
        this.orderStatus = orderStatus;
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

    public Set<OrderItemRequest> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItemRequest> orderItems) {
        this.orderItems = orderItems;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
