package com.lauder.app.ecommapp.dto.request.cart;


import com.lauder.app.ecommapp.model.UsersModel;
import com.lauder.app.ecommapp.model.ProductModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Set;

public class CartRequest {

    @NotBlank(message = "CartId is required")
    private Long cartId;

    @NotBlank(message = "Customer Id required")
    private UsersModel customerId;

    @NotBlank(message = "Ever Product Added to the cart display ")
    private Long productId;

    @NotBlank(message = "All Product Added display their name")
    private Set<ProductModel> productName;

    @NotBlank(message = "Quantity must be positive")
    @Positive
    private int quantity;

    @NotBlank(message = "Price of each product needs to be display")
    private BigDecimal price;

    @NotBlank(message = "Total of each product required ")
    private  BigDecimal total;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public UsersModel getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UsersModel customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductModel getProductName() {
        return (ProductModel) productName;
    }

    public void setProductName(Set<ProductModel> productName) {
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = price.multiply(BigDecimal.valueOf(quantity));

    }
}
