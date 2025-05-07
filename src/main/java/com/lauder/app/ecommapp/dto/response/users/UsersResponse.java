package com.lauder.app.ecommapp.dto.response.users;

import jakarta.validation.constraints.Email;


public class UsersResponse {

    private Long customerId;

    private String customerName;

    @Email
    private String email;

    private String password;

    private String phoneNumber;

    private String AddressLine1;

    private String AddressLine2;

    private String AddressLine3;

    private String city;

    private String county;

    private String Eircode;

    private String country;

    public UsersResponse() {

    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    // Buiider
    public  static class Builder {
        private final UsersResponse usersResponse;

        public Builder() {
            usersResponse = new UsersResponse();
        }

        public Builder customerId(Long customerId) {
            usersResponse.customerId = customerId;
            return this;
        }

        public Builder customerName(String customerName) {
            usersResponse.customerName = customerName;
            return this;
        }

        public Builder email(String email) {
            usersResponse.setEmail(email); // Using setter to ensure lowercase conversion
            return this;
        }

        public Builder password(String password) {
            usersResponse.password = password;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            usersResponse.phoneNumber = phoneNumber;
            return this;
        }

        public Builder addressLine1(String addressLine1) {
            usersResponse.AddressLine1 = addressLine1;
            return this;
        }

        public Builder addressLine2(String addressLine2) {
            usersResponse.AddressLine2 = addressLine2;
            return this;
        }

        public Builder addressLine3(String addressLine3) {
            usersResponse.AddressLine3 = addressLine3;
            return this;
        }

        public Builder city(String city) {
            usersResponse.city = city;
            return this;
        }

        public Builder county(String county) {
            usersResponse.county = county;
            return this;
        }

        public Builder eircode(String eircode) {
            usersResponse.Eircode = eircode;
            return this;
        }

        public Builder country(String country) {
            usersResponse.country = country;
            return this;
        }

        public UsersResponse build() {
            return usersResponse;
        }
    }
}

