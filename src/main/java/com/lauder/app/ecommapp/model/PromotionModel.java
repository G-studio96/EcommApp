package com.lauder.app.ecommapp.model;

import jakarta.persistence.*;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PROMOTIONS")
public class PromotionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROMOTION_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "promoters")
    private Promoter promoter;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "promo_codes", joinColumns = @JoinColumn(name = "promoters_id"))
    @Column(name = "PROMO_CODE")
    private Set<String> promoCode;

    @Column(name = "FOLLOWERS")
    private int followers;

    @Column(name = "DISCOUNT")
    private float discount;

    @Column(name = "PROMO_CODE_COUNT")
    private int promoCodeCount;

    @ManyToOne
    @CollectionTable(name = "promotion_earnings", joinColumns = @JoinColumn(name = "promoters_id"))
    @JoinColumn(name = "TOTAL")
    private OrderModel earnedAmount;

    @Column(name = "CREATED_AT")
    private LocalDateTime localDateTime;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedTime;

    public PromotionModel() {

    }

    public PromotionModel(Long id, Promoter promoter, Set<String> promoCode, int followers, float discount) {
        this.id = id;
        this.promoter = promoter;
        this.promoCode = promoCode;
        this.followers = followers;
        this.discount = discount;


    }


    public enum Platform {
        INSTAGRAM,
        TIKTOK,
        YOUTUBE,
        X
    }

    private static Set<String> existingPromoCodes = new HashSet<>();


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

    public Set<String> getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(Set<String> promoCode) {
        this.promoCode = promoCode;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getPromoCodeCount() {
        return promoCodeCount;
    }

    public void setPromoCodeCount(int promoCodeCount) {
        this.promoCodeCount = promoCodeCount;
    }


    public static Set<String> getExistingPromoCodes() {
        return existingPromoCodes;
    }

    public static void setExistingPromoCodes(Set<String> existingPromoCodes) {
        PromotionModel.existingPromoCodes = existingPromoCodes;
    }

    public OrderModel getEarnedAmount() {
        return earnedAmount;
    }

    public void setEarnedAmount(OrderModel earnedAmount) {
        this.earnedAmount = earnedAmount;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String makePromoCode(String socialHandle) {
        SecureRandom random = new SecureRandom();
        String base = socialHandle.length() > 3 ? socialHandle.substring(0, 3).toUpperCase() : socialHandle.toUpperCase();
        StringBuilder builder = new StringBuilder(base + "-");
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(chars.length());
            builder.append(chars.charAt(index));
        }
        return builder.toString();
    }

    public void makeDiscountPercent(int followers , Platform platform) {
        int[] followingTiers = {100_000, 500_000, 1_000_000};
        float baseDiscount;

        if (followers < followingTiers[0]) {
            baseDiscount = 5.0f;
        } else if (followers < followingTiers[1]) {
            baseDiscount = 10.0f;
        } else if (followers < followingTiers[2]) {
            baseDiscount = 15.0f;
        } else {
            baseDiscount = 20.0f;
        }

        float platformBonus = 0.0f;

        switch (platform) {

            case INSTAGRAM:
                platformBonus = 2.0f;
                break;
            case TIKTOK:
                platformBonus = 3.0f;
                break;
            case YOUTUBE:
                platformBonus = 2.5f;
                break;

            case X:
                platformBonus = 1.5f;
                break;
            default:
                platformBonus = 1.0f;
        }

        float finalDiscount = Math.min(baseDiscount + platformBonus, 25.0f);
        this.setDiscount(finalDiscount);
    }


    public void generatePromotion(String socialHandle, Platform platform, int followers) {

        this.followers = followers;

        String code;
        do {
            code = makePromoCode(socialHandle);
        } while (existingPromoCodes.contains(code));

        existingPromoCodes.add(code);
        this.promoCode.add(code);
        this.promoCodeCount = this.promoCode.size();

        makeDiscountPercent(followers, platform);
    }

    public static Platform determinePlatformFromHandle(String socialHandle) {
        if (socialHandle == null) return null;

        String lowerHandle = socialHandle.toLowerCase();
        if (lowerHandle.contains("instagram") || lowerHandle.contains("ig")) {
            return Platform.INSTAGRAM;
        } else if (lowerHandle.contains("tiktok") || lowerHandle.contains("tt")) {
            return Platform.TIKTOK;
        } else if (lowerHandle.contains("youtube") || lowerHandle.contains("yt")) {
            return Platform.YOUTUBE;
        } else if (lowerHandle.contains("twitter") || lowerHandle.contains("x")) {
            return Platform.X;
        }
        return null;
    }
}

