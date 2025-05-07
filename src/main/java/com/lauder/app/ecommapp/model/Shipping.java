package com.lauder.app.ecommapp.model;

import jakarta.persistence.*;

import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "Shipping")
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHIPPING_ID")
    private Long shippingId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private OrderModel order;

    @ManyToMany
    @JoinColumn(name = "PRODUCT_ID")
    private Set<ProductModel> product;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private UsersModel customers;

    @CollectionTable(name = "TRACKING_NUMBER", joinColumns = @JoinColumn("SHIPPING_ID"))
    @Column(name = "TRACKING_NUMBER")
    private Long trackingNumber;

    private


    private static Map<Long, Long>


}
