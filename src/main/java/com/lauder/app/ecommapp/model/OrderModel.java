package com.lauder.app.ecommapp.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;


@Data
@Entity
@Table(name = "orders")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID", unique = true)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private UsersModel customer;

    @ManyToOne
    @JoinColumn(name = "PAYMENT_ID")
    private PaymentModel paymentId;

    @ManyToOne
    @JoinColumn(name = "STATUS_ID")
    private OrderStatus statusId;

    @ManyToMany
    @JoinTable(
            name = "order_status",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "status_id"))
    private Set<OrderStatus> orderStatus;

    @Column(name = "ORDER_DATE")
    private LocalDateTime orderDate;

    @Column(name = "TOTAL")
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;

    @Column(name = "CREATED_AT")
    private LocalDateTime localDateTime;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedTime;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public UsersModel getCustomer() {
        return customer;
    }

    public void setCustomer(UsersModel customer) {
        this.customer = customer;
    }


    public OrderStatus getStatus() {
        return statusId;
    }

    public void setStatus(OrderStatus statusId) {
        this.statusId = statusId;
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

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public PaymentModel getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(PaymentModel paymentId) {
        this.paymentId = paymentId;
    }

    public Set<OrderStatus> getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Set<OrderStatus> orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderStatus getStatusId() {
        return statusId;
    }

    public void setStatusId(OrderStatus statusId) {
        this.statusId = statusId;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }
}




