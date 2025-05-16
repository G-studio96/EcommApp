package com.lauder.app.ecommapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

@Entity
@Table(name = "earnings")
public class RebateEarned {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rebate_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promoter_id", nullable = false)
    private Promoter promoter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id", nullable = false)
    private PromotionModel promotion;

    @Positive
    @Column(name = "earnings", nullable = false)
    private BigDecimal earnings = BigDecimal.ZERO;

    @Column(name = "month", nullable = false)
    private YearMonth month;

    @Column(name = "start_month", nullable = false)
    private LocalDateTime startMonth;

    @Column(name = "end_month")
    private LocalDateTime endMonth;

    @PrePersist
    protected void onCreate() {
        if (startMonth == null) {
            startMonth = LocalDateTime.now();
        }
        if (month == null) {
            month = YearMonth.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        endMonth = LocalDateTime.now();
    }

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

    public PromotionModel getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionModel promotion) {
        this.promotion = promotion;
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
        return startMonth;
    }

    public void setStartMonth(LocalDateTime startMonth) {
        this.startMonth = startMonth;
    }

    public LocalDateTime getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(LocalDateTime endMonth) {
        this.endMonth = endMonth;
    }

}
