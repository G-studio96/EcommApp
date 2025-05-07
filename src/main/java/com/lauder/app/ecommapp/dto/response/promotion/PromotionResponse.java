package com.lauder.app.ecommapp.dto.response.promotion;

import com.lauder.app.ecommapp.model.PromotionModel;

import java.util.Set;

public class PromotionResponse {
    private Long promotersId;
    private Set<String> socialHandle;

    private Set<PromotionModel.Platform> platform;

    private Set<String> promoCode;

    private int followers;

    private float discount;

    private int promoCodeCount;

    public PromotionResponse() {

    }

    public PromotionResponse(Long promotersId, Set<String> socialHandle,
                             Set<PromotionModel.Platform> platform,
                             Set<String> promoCode, int followers, float discount,
                             int promoCodeCount) {
        this.promotersId = promotersId;
        this.socialHandle = socialHandle;
        this.platform = platform;
        this.promoCode = promoCode;
        this.followers = followers;
        this.discount = discount;
        this.promoCodeCount =promoCodeCount;
    }

    public Long getPromotersId() {
        return promotersId;
    }

    public void setPromotersId(Long promotersId) {
        this.promotersId = promotersId;
    }

    public Set<String> getSocialHandle() {
        return socialHandle;
    }

    public void setSocialHandle(Set<String> socialHandle) {
        this.socialHandle = socialHandle;
    }

    public Set<PromotionModel.Platform> getPlatform() {
        return platform;
    }

    public void setPlatform(Set<PromotionModel.Platform> platform) {
        this.platform = platform;
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

    @Override
    public String toString() {
        return "PromotionResponse{" +
                "promotersId=" + promotersId +
                ", socialHandle='" + socialHandle + '\'' +
                ", platform=" + platform +
                ", promoCode='" + promoCode + '\'' +
                ", followers=" + followers +
                ", discount=" + discount +
                ", promoCodeCount=" + promoCodeCount +
                '}';
    }

}
