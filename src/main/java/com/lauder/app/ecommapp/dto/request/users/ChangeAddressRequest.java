package com.lauder.app.ecommapp.dto.request.users;

import jakarta.validation.constraints.NotBlank;

public class ChangeAddressRequest {

    @NotBlank(message = "Address line one is required")
    private String AddressLine1;

    @NotBlank(message = "Address line two is required")
    private String AddressLine2;

    @NotBlank(message = "Address line three is required")
    private String AddressLine3;

    @NotBlank(message = "Address line three is required")
    private String city;

    @NotBlank(message = "County is required")
    private String county;

    @NotBlank(message = "Eircode is required")
    private String Eircode;

    @NotBlank(message = "country is required")
    private String country;


    public String getAddressLine1() {
        return AddressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        AddressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return AddressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        AddressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return AddressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        AddressLine3 = addressLine3;
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
        return Eircode;
    }

    public void setEircode(String eircode) {
        Eircode = eircode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
