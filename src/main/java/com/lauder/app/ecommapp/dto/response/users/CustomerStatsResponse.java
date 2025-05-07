package com.lauder.app.ecommapp.dto.response.users;

import java.math.BigDecimal;

public class CustomerStatsResponse {
    private long ordersMade;

    private BigDecimal amountSpent;

    private int cartItems;


    public CustomerStatsResponse(long ordersMade, BigDecimal amountSpent, int cartItems) {
        this.ordersMade = ordersMade;
        this.amountSpent = amountSpent;
        this.cartItems = cartItems;
    }
}
