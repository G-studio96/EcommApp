package com.lauder.app.ecommapp.dto.response.promoter;

import com.lauder.app.ecommapp.model.PromotionModel;
import com.lauder.app.ecommapp.model.Role;
import jakarta.validation.constraints.Email;

import java.util.Set;

public class PromoterResponse {

    private Long promotersId;

    private  String name;

    @Email
    private String email;

    private String password;

    private Set<Role.Roles> roles;

    private Set<String> socialHandle;

    private Set<PromotionModel.Platform> platform;

    private String AddressLineOne;

    private String AddressLineTwo;

    private String AddressLineThree;

    private String city;

    private String county;

    private String postCode;

    private String country;

    public Long getPromotersId() {
        return promotersId;
    }

    public void setPromotersId(Long promotersId) {
        this.promotersId = promotersId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role.Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role.Roles> roles) {
        this.roles = roles;
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

    public String getAddressLineOne() {
        return AddressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        AddressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return AddressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        AddressLineTwo = addressLineTwo;
    }

    public String getAddressLineThree() {
        return AddressLineThree;
    }

    public void setAddressLineThree(String addressLineThree) {
        AddressLineThree = addressLineThree;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
