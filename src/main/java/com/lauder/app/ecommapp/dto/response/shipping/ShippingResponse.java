package com.lauder.app.ecommapp.dto.response.shipping;

import com.lauder.app.ecommapp.model.OrderModel;
import com.lauder.app.ecommapp.model.ProductModel;
import com.lauder.app.ecommapp.model.ShippingStatus;
import com.lauder.app.ecommapp.model.UsersModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class ShippingResponse {
    private Long shippingId;


    private OrderModel order;

    private Set<ShippingStatus> shippingStatuses;


    private Set<ProductModel> products;


    private List<String> productName;

    private int quantity;

    private UsersModel customers;

    private String phoneNumber;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private String city;

    private String county;

    private String country;

    private String eircode;

    private Long trackingNumber;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Long getShippingId() {
        return shippingId;
    }

    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public Set<ShippingStatus> getShippingStatuses() {
        return shippingStatuses;
    }

    public void setShippingStatuses(Set<ShippingStatus> shippingStatuses) {
        this.shippingStatuses = shippingStatuses;
    }

    public Set<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductModel> products) {
        this.products = products;
    }

    public List<String> getProductName() {
        return productName;
    }

    public void setProductName(List<String> productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public UsersModel getCustomers() {
        return customers;
    }

    public void setCustomers(UsersModel customers) {
        this.customers = customers;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEircode() {
        return eircode;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(Long trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
