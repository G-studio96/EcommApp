package com.lauder.app.ecommapp.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "earnings")
public class RebateEarned {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rebate_Id")
    private Long id;

    @ManyToMany(mappedBy = "promoter", cascade = CascadeType.ALL)
    private Promoter promoter;

    @ManyToMany(mappedBy = "promotion", cascade = CascadeType.ALL)
    private PromotionModel promotion;

    @Column(name = "EARNINGS")
    private BigDecimal earnings;


    private static Map<Promoter, PromotionModel> countAmountPromoCodesUsed;

    private static Map<Promoter, BigDecimal> payout = new HashMap<>();

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

    public static Map<Promoter, PromotionModel> getCountAmountPromoCodesUsed() {
        return countAmountPromoCodesUsed;
    }

    public static void setCountAmountPromoCodesUsed(Map<Promoter, PromotionModel> countAmountPromoCodesUsed) {
        RebateEarned.countAmountPromoCodesUsed = countAmountPromoCodesUsed;
    }

    public static Map<Promoter, BigDecimal> getPayout() {
        return payout;
    }

    public static void setPayout(Map<Promoter, BigDecimal> payout) {
        RebateEarned.payout = payout;
    }
}
