package com.lauder.app.ecommapp.dto.response.order;


import com.lauder.app.ecommapp.model.OrderModel;
import com.lauder.app.ecommapp.model.ProductModel;

import java.math.BigDecimal;

public class OrderItemResponse {

    private Long OrderItemsId;

    private OrderModel orderId;

    private ProductModel productId;

    private int quantity;

    private BigDecimal price;

    public Long getOrderItemId() {
        return OrderItemsId;
    }

    public void setOrderItemId(Long orderItemsId) {
        OrderItemsId = orderItemsId;
    }

    public OrderModel getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderModel orderId) {
        this.orderId = orderId;
    }

    public ProductModel getProductId() {
        return productId;
    }

    public void setProductId(ProductModel productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
