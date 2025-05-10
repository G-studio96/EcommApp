package com.lauder.app.ecommapp.model;


import com.lauder.app.ecommapp.converter.PasswordEncryptionConverter;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "promoters")
public class Promoter {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROMOTERS_ID", unique = true)
    private Long promotersId;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private UsersModel customerId;

    @ManyToMany(mappedBy = "promoters")
    private Set<UsersModel> customers;


    @Column(name = "NAME")
    private  String name;

    @Column(name = "EMAIL")
    @jakarta.validation.constraints.Email
    private String email;

    @Column(name = "PASSWORD")
    @Convert(converter = PasswordEncryptionConverter.class)
    private String password;

    @ManyToMany
    @Column(name = "ROLE")
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "promoter_roles", joinColumns = @JoinColumn(name = "promoter_id"))
    private Set<Role.Roles> roles;

    @Column(name = "SOCIAL_HANDLE")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "promoter_social_handles", joinColumns = @JoinColumn(name = "promoter_id"))
    private Set<String> socialHandle = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "promoter_platforms", joinColumns = @JoinColumn(name = "promoter_id"))
    @Column(name = "PLATFORM")
    private Set<PromotionModel.Platform> platform = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COMMISSION")
    private RebateEarned commission;

    @Column(name = "ADDRESS_LINE_ONE")
    private String AddressLineOne;

    @Column(name = "ADDRESS_LINE_TWO")
    private String AddressLineTwo;

    @Column(name = "ADDRESS_LINE_THREE")
    private String AddressLineThree;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTY")
    private String county;

    @Column(name = "Eircode")
    private String postCode;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CREATED_AT")
    private LocalDateTime localDateTime;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedTime;

    public Long getPromotersId() {
        return promotersId;
    }

    public void setPromotersId(Long promotersId) {
        this.promotersId = promotersId;
    }

    public UsersModel getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UsersModel customerId) {
        this.customerId = customerId;
    }

    public Set<UsersModel> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<UsersModel> customers) {
        this.customers = customers;
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
}
