package com.lauder.app.ecommapp.mapper.usersMapper;


import com.lauder.app.ecommapp.dto.request.users.UsersRequest;
import com.lauder.app.ecommapp.dto.response.users.UsersResponse;
import com.lauder.app.ecommapp.model.UsersModel;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {

    public UsersModel toEntity(UsersRequest usersRequest) {
        if (usersRequest == null ) {
            return  null;
        }

        UsersModel model = new UsersModel();
        model.setId(usersRequest.getCustomerId());
        model.setName(usersRequest.getCustomerName());
        model.setEmail(usersRequest.getEmail());
        model.setPassword(usersRequest.getPasswords());
        model.setPhone_Number(usersRequest.getPhoneNumber());
        model.setAddressLine1(usersRequest.getAddressLine1());
        model.setAddressLine2(usersRequest.getAddressLine2());
        model.setAddressLine3(usersRequest.getAddressLine3());
        model.setCity(usersRequest.getCity());
        model.setCounty(usersRequest.getCounty());
        model.setEircode(usersRequest.getEircode());
        model.setCountry(usersRequest.getCountry());

        return model;


    }

    public UsersResponse toResponse(UsersModel model) {
        if( model == null) {
            return null;
        }

        return new UsersResponse.Builder()
                .customerId(model.getId())
                .customerName(model.getName())
                .email(model.getEmail())
                .password(model.getPassword())
                .phoneNumber(model.getPhone_Number())
                .addressLine1(model.getAddressLine1())
                .addressLine2(model.getAddressLine2())
                .addressLine3(model.getAddressLine3())
                .city(model.getCity())
                .county(model.getCounty())
                .eircode(model.getEircode())
                .country(model.getCountry()).build();
    }
}
