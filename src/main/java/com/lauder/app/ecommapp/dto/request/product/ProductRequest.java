package com.lauder.app.ecommapp.dto.request.product;

import com.lauder.app.ecommapp.model.CategoriesModel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public class ProductRequest {

    @NotBlank(message = "product ID is required")
    private Long productId;

    @NotBlank(message = "Category ID is required")
    private CategoriesModel categoryId;

    @NotBlank(message =  "Category Name isa required")
    private String categoryName;
    @NotBlank(message = "Product name is required")
    private String productName;

    @Min( value = 0, message = "Quantity is required")
    private int quantity;

    @NotBlank(message = "Price is required")
    @Positive(message = "Price must be greater than save")
    private BigDecimal price;

    @NotBlank(message = "TimeStamp is required")
    private LocalDateTime localDateTime;

    @NotBlank(message = "Updated Timestamp required")
    private LocalDateTime updatedTime;

    @NotBlank(message = "URL is required")
    private String url;


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public CategoriesModel getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(CategoriesModel categoryId) {
        this.categoryId = categoryId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
