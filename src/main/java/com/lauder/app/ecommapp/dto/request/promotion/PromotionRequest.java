package com.lauder.app.ecommapp.dto.request.promotion;

import com.lauder.app.ecommapp.model.PromotionModel;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public class PromotionRequest {

    @Id
    @NotBlank(message = "Promoter Id  is a must")
    private  Long promotersId;

    @NotBlank(message = "Requires name")
    private String name;

    @NotBlank(message = "Requires email")
    private String email;

    @NotBlank(message = "Requires password")
    private String password;

    @NotBlank(message = "Social handle is required")
    private  String socialHandle;

    @NotBlank(message = "must be a influencers from social media platform")
    private PromotionModel.Platform platform;

    @Min(value = 0, message = "Followers count must be positive")
    private  int followers;

    @Min(value = 0 )
    @Max(value =  20)
    private Set<String> promoCode;




    public PromotionRequest() {

    }

    public PromotionRequest(String socialHandle, PromotionModel.Platform platform, int followers, Set<String> promoCode) {
        this.socialHandle = socialHandle;
        this.platform = platform;
        this.followers = followers;
        this.promoCode = promoCode;
    }

    public Long getPromotersId() {
        return promotersId;
    }

    public void setPromotersId(Long promotersId) {
        this.promotersId = promotersId;
    }

    public String getSocialHandle() {
        return socialHandle;
    }

    public void setSocialHandle(String socialHandle) {
        this.socialHandle = socialHandle;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PromotionModel.Platform getPlatform() {
        return platform;
    }

    public void setPlatform(PromotionModel.Platform platform) {
        this.platform = platform;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public Set<String> getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(Set<String> promoCode) {
        this.promoCode = promoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    @Override
    public String toString() {
        return "PromotionRequest{" +
                "socialHandle='" + socialHandle + '\'' +
                ", platform=" + platform +
                ", followers=" + followers +
                ", promoCode='" + promoCode + '\'' +
                '}';
    }
}
