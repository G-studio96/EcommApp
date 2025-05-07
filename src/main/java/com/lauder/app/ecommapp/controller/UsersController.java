package com.lauder.app.ecommapp.controller;


import com.lauder.app.ecommapp.dto.request.users.ChangeAddressRequest;
import com.lauder.app.ecommapp.dto.request.users.ChangePasswordRequest;
import com.lauder.app.ecommapp.dto.request.users.UsersRequest;
import com.lauder.app.ecommapp.dto.response.users.UsersResponse;
import com.lauder.app.ecommapp.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountException;
import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/api/customer")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UsersResponse> createCustomer(@Valid @RequestBody UsersRequest usersRequest) {
        UsersResponse createdCustomer = usersService.createSignUpDetails(usersRequest);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }


    @PostMapping("/deliver-details")
    public ResponseEntity<UsersResponse> updateDeliveryDetails(@Valid @RequestBody UsersRequest request) {
        UsersResponse updatedCustomer =  usersService.customerDeliveryDetails(request);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PostMapping("/change-password")
    public ResponseEntity<UsersResponse> changePassword(@Valid @RequestBody ChangePasswordRequest request, Principal principal) {
        try {
            UsersResponse updatedCustomer = usersService.changePassword(principal, request);
            return ResponseEntity.ok(updatedCustomer);
        } catch (AccountException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }


    @PostMapping("/change-address")
    public ResponseEntity<UsersResponse> changeAddress(@Valid @RequestBody ChangeAddressRequest request, Principal principal) {
        try {
            UsersResponse updatedCustomer = usersService.changeDeliveryAddress(principal, request);
            return  ResponseEntity.ok(updatedCustomer);
        } catch (AccountException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
