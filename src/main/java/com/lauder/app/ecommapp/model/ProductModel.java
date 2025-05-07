package com.lauder.app.ecommapp.model;

import jakarta.persistence.*;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "products")
public class ProductModel {

    @Id
    @Column(name ="ID")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private CategoriesModel categoryId;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime localDateTime;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedTime;

    @Column(name ="URL")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CategoriesModel getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(CategoriesModel categoryId) {
        this.categoryId = categoryId;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
