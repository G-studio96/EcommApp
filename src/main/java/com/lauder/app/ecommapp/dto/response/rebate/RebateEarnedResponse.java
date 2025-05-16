package com.lauder.app.ecommapp.dto.response.rebate;

import com.lauder.app.ecommapp.model.Promoter;
import com.lauder.app.ecommapp.model.PromotionModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

public class RebateEarnedResponse {

    private Long id;

    private Promoter promoter;

    private PromotionModel promotionCode;

    private BigDecimal earnings;

    private YearMonth month;

    private LocalDateTime StartMonth;

    private LocalDateTime endMonth;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Promoter getPromoter() {
        return promoter;
    }

    public void setPromoter(Promoter promoter) {
        this.promoter = promoter;
    }

    public PromotionModel getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(PromotionModel promotionCode) {
        this.promotionCode = promotionCode;
    }

    public BigDecimal getEarnings() {
        return earnings;
    }

    public void setEarnings(BigDecimal earnings) {
        this.earnings = earnings;
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }

    public LocalDateTime getStartMonth() {
        return StartMonth;
    }

    public void setStartMonth(LocalDateTime startMonth) {
        StartMonth = startMonth;
    }

    public LocalDateTime getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(LocalDateTime endMonth) {
        this.endMonth = endMonth;
    }
}
