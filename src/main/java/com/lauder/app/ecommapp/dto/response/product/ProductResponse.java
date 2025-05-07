package com.lauder.app.ecommapp.dto.response.product;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductResponse {

    private Long productId;

    private Long categoriesId;

    private String categoryName;

    private String productName;

    private int quantity;

    private BigDecimal price;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;

    private  String url;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Long categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class Builder {
        private final ProductResponse productResponse;

        public Builder() {
            productResponse = new ProductResponse();
        }

        public Builder productId(Long productId) {
            productResponse.productId = productId;
            return this;
        }
        public Builder categoryId(Long categoryId) {
            productResponse.categoriesId = categoryId;
            return this;
        }

        public Builder categoryName(String categoryName) {
            productResponse.categoryName = categoryName;
            return this;
        }

        public Builder productName(String productName) {
            productResponse.productName = productName;
            return this;
        }

        public Builder price(BigDecimal price) {
            productResponse.price = price;
            return this;
        }

        public Builder quantity(int quantity){
            productResponse.quantity = quantity;
            return this;
        }

        public Builder createdTime(LocalDateTime createdTime) {
            productResponse.createdTime = createdTime;
            return this;
        }

        public  Builder updatedTime(LocalDateTime updatedTime) {
            productResponse.updatedTime = updatedTime;
            return this;
        }

        public  Builder url(String url) {
            productResponse.url = url;
            return this;
        }

        public ProductResponse build() {
            return productResponse;
        }
    }
}
