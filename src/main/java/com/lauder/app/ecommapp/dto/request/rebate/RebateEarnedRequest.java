package com.lauder.app.ecommapp.dto.request.rebate;

import com.lauder.app.ecommapp.model.Promoter;
import com.lauder.app.ecommapp.model.PromotionModel;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

public class RebateEarnedRequest {

    @NotBlank(message = "Rebate Id must be generated")
    private Long id;

    @NotBlank(message = "Promoter name required")
    private Promoter promoter;

    @NotBlank(message = "Promotion code required created for promoter")
    private PromotionModel promotionCode;

    @NotBlank(message = "Earnings made by promoters")
    private BigDecimal earnings;

    @NotBlank(message = "monthly earnings")
    @DateTimeFormat(pattern = "Jan, Feb, March, April, May, June, July, Aug, Sept, Oct, Nov, Dec")
    private YearMonth month;

    @NotBlank(message = "start of month which all promoter start the month on zero")
    private LocalDateTime StartMonth;

    @NotBlank(message = "End of the month which all promoters will have updated ")
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
