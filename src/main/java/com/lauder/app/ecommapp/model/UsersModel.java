package com.lauder.app.ecommapp.model;

import com.lauder.app.ecommapp.converter.PasswordEncryptionConverter;
import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name= "customers")
public class UsersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASSWORD")
    @Convert(converter = PasswordEncryptionConverter.class)
    private String password;

    @Column(name = "Email")
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "CUSTOMER_ROLES", joinColumns = @JoinColumn(name = "customer_id"),
    inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> role;

    @ManyToMany
    @JoinTable(
            name = "customer_promoter",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "promoter_id")
    )
    private Set<PromotionModel> promoters;


    public UsersModel() {

    }

    public UsersModel(String name, String email, String password, Set<Role> role) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;


    @Column(name = "ADDRESS_LINE_ONE")
    private String addressLine1;

    @Column(name = "ADDRESS_LINE_TWO")
    private String addressLine2;

    @Column(name = "ADDRESS_LINE_THREE")
    private String addressLine3;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTY")
    private String county;

    @Column(name = "EIRCODE")
    private String eircode;

    @Column(name = "COUNTRY")
    private String country;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.email = email.toLowerCase();
    }



    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getPhone_Number() {
        return phoneNumber;
    }

    public void setPhone_Number(String phone_Number) {
        this.phoneNumber = phone_Number;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
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

    public String getEircode() {
        return eircode;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<PromotionModel> getPromoters() {
        return promoters;
    }

    public void setPromoters(Set<PromotionModel> promoters) {
        this.promoters = promoters;
    }


}

