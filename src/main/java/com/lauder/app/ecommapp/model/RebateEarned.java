package com.lauder.app.ecommapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private BigDecimal earnings;

    @Column(name = "month", nullable = false)
    private String month;

    @Column(name = "start_month")
    private LocalDateTime startMonth;

    @Column(name = "end_month")
    private LocalDateTime endMonth;

    @PrePersist
    protected void onCreate() {
        startMonth = LocalDateTime.now();
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



}
