package com.lauder.app.ecommapp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Shipping")
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHIPPING_ID", unique = true)
    private Long shippingId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", unique = true)
    private OrderModel order;

    @ManyToMany
    @JoinTable(
            name = "shipping_products",
            joinColumns = @JoinColumn(name = "SHIPPING_ID "),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private Set<ProductModel> products;

    @ElementCollection
    @CollectionTable(name = "shipping_product_names",
            joinColumns = @JoinColumn(name = "SHIPPING_ID"))
    @Column(name = "PRODUCT_NAME")
    private List<String> productName;

    @Column(name = "Quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private UsersModel customers;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ADDRESS_LINE_ONE")
    private String addressLine1;

    @Column(name = "ADDRESS_LINE_TWO")
    private String addressLine2;

    @Column(name = "ADDRESS_LINE_THREE")
    private String addressLine3;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTY")
    private String county;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "EIRCODE")
    private String eircode;

    public Shipping() {

    }

    public Shipping(
            Long shippingId, OrderModel order,
            Set<ProductModel> products, List<String> productName,
            int quantity, UsersModel customers, String phoneNumber,
            String addressLine1, String addressLine2, String addressLine3,
            String city, String county, String country, String eircode) {
        this.shippingId = shippingId;
        this.order = order;
        this.products = products;
        this.productName = productName;
        this.quantity = quantity;
        this.customers = customers;
        this.phoneNumber = phoneNumber;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.city = city;
        this.county = county;
        this.country = country;
        this.eircode = eircode;

    }
    @CollectionTable(name = "TRACKING_NUMBER", joinColumns = @JoinColumn("SHIPPING_ID"))
    @Column(name = "TRACKING_NUMBER", unique = true)
    private Long trackingNumber;

    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime created;

    @Column(name = "UPDATED_AT", updatable = true)
    private LocalDateTime updated;


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

    public Long getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(Long trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
