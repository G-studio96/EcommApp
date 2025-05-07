package com.lauder.app.ecommapp.dto.response.cart;


import com.lauder.app.ecommapp.model.UsersModel;

import java.math.BigDecimal;
import java.util.Set;

public class CartResponse {

    private Long cartId;

    private UsersModel customerId;

    private Set<CartItemResponse> items;

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

    public Set<CartItemResponse> getItems() {
        return items;
    }

    public void setItems(Set<CartItemResponse> items) {
        this.items = items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
