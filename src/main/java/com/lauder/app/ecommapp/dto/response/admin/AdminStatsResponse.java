package com.lauder.app.ecommapp.dto.response.admin;

import java.math.BigDecimal;

public class AdminStatsResponse {
    private long totalUsers;

    private long totalOrders;

    private BigDecimal totalRevenue;

    private long totalPromoters;

    public AdminStatsResponse(long totalUsers, long totalOrders, BigDecimal totalRevenue, long totalPromoters) {
        this.totalUsers = totalUsers;
        this.totalOrders = totalOrders;
        this.totalRevenue = totalRevenue;
        this.totalPromoters = totalPromoters;
    }
}
