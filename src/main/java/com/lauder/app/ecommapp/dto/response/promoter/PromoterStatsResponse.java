package com.lauder.app.ecommapp.dto.response.promoter;

import java.math.BigDecimal;

public class PromoterStatsResponse {
    private long totalPromotions;
    private BigDecimal totalEarnings;

    public PromoterStatsResponse(long totalPromotions, BigDecimal totalEarnings) {
        this.totalPromotions = totalPromotions;
        this.totalEarnings = totalEarnings;
    }
}
