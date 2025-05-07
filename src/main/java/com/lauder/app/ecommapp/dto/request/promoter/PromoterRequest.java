package com.lauder.app.ecommapp.dto.request.promoter;

import com.lauder.app.ecommapp.model.PromotionModel;
import com.lauder.app.ecommapp.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public class PromoterRequest {

    @NotBlank(message = "Requires Promoter Id")
    private Long promotersId;

    @NotBlank(message = "Requires Promotes name")
    private String name;

    @NotBlank(message = "Requires Promoter email address")
    @Email
    private String email;

    @NotBlank(message = "Requires passwords")
    private String password;

    @NotBlank(message = "Requires the role of Promoters")
    private Set<Role.Roles> roles;

    @NotBlank(message = "Requires promoters social handle")
    private Set<String> socialHandle;

    @NotBlank(message = "Requires the type of platform used by promoters")
    private Set<PromotionModel.Platform> platform;

    @NotBlank(message = "Requires first line of address")
    private String AddressLineOne;

    @NotBlank(message = "Requires second line of address")
    private String AddressLineTwo;

    @NotBlank(message = "Requires three line of address")
    private String AddressLineThree;

    @NotBlank(message = "Requires city")
    private String city;

    @NotBlank(message = "Requires county")
    private String county;

    @NotBlank(message = "Requires postcode")
    private String postCode;

    @NotBlank(message = "Requires country")
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

