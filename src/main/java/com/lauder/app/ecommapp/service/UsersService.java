package com.lauder.app.ecommapp.service;


import com.lauder.app.ecommapp.dto.request.users.ChangeAddressRequest;
import com.lauder.app.ecommapp.dto.request.users.ChangePasswordRequest;
import com.lauder.app.ecommapp.dto.request.users.UsersRequest;
import com.lauder.app.ecommapp.dto.response.users.UsersResponse;
import com.lauder.app.ecommapp.mapper.usersMapper.UsersMapper;
import com.lauder.app.ecommapp.model.UsersModel;
import com.lauder.app.ecommapp.repo.IUsersRepo;

import com.lauder.app.ecommapp.util.AuditService;
import com.lauder.app.ecommapp.util.PasswordAttempt;
import com.lauder.app.ecommapp.util.PasswordHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import java.security.Principal;
import java.util.Optional;


@Service
public class UsersService {

    private final IUsersRepo iCustomerRepo;

    private final PasswordEncoder passwordEncoder;

    private final UsersMapper usersMapper;

    private final PasswordAttempt passwordAttempt;

    private final PasswordHistory passwordHistory;

    private final AuditService auditService;

    @Autowired
    public UsersService(IUsersRepo iCustomerRepo, PasswordEncoder passwordEncoder, UsersMapper usersMapper,
                        PasswordAttempt passwordAttempt, PasswordHistory passwordHistory, AuditService auditService) {
        this.iCustomerRepo = iCustomerRepo;
        this.passwordEncoder = passwordEncoder;
        this.usersMapper = usersMapper;
        this.passwordAttempt = passwordAttempt;
        this.passwordHistory = passwordHistory;
        this.auditService = auditService;
    }

    public UsersResponse createSignUpDetails(UsersRequest usersRequest) {
        UsersModel user = new UsersModel();

        user.setEmail(usersRequest.getEmail());

        user.setName(usersRequest.getCustomerName());

        user.setPhone_Number(usersRequest.getPhoneNumber());

        user.setPassword(passwordEncoder.encode(usersRequest.getPasswords()));


        UsersModel savedCustomer = iCustomerRepo.save(user);

        return usersMapper.toResponse(savedCustomer);
    }

    public UsersResponse customerDeliveryDetails(UsersRequest usersRequest) {

        UsersModel deliveryDetails = new UsersModel();

        deliveryDetails.setAddressLine1(usersRequest.getAddressLine1());

        deliveryDetails.setAddressLine2(usersRequest.getAddressLine2());

        deliveryDetails.setAddressLine3(usersRequest.getAddressLine3());

        deliveryDetails.setCity(usersRequest.getCity());

        deliveryDetails.setCounty(usersRequest.getCounty());

        deliveryDetails.setCountry(usersRequest.getCountry());

        UsersModel savedDeliveryDetail = iCustomerRepo.save(deliveryDetails);

        return usersMapper.toResponse(savedDeliveryDetail);

    }

    public UsersResponse changePassword(Principal connectedUser, ChangePasswordRequest request) throws AccountException {

        passwordAttempt.checkRateLimit(connectedUser.getName());


        Optional<UsersModel> users = iCustomerRepo.findByEmail(connectedUser.getName());

        if (users.isEmpty()) {
            throw new AccountException("Authentication failed");
        }


        UsersModel customer = users.get();

        if(!passwordEncoder.matches(request.getCurrentPassword(), customer.getPassword())) {

            passwordAttempt.recordFailedAttempt(connectedUser.getName());

            throw new RuntimeException("Current password is incorrect");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("new passwords don't match");

        }


        customer.setPassword(passwordEncoder.encode(request.getNewPassword()));

        passwordHistory.savePassword(customer.getId(), customer.getPassword());

        passwordAttempt.resetFailedAttempts(connectedUser.getName());

        UsersModel updateCustomer = iCustomerRepo.save(customer);

        auditService.logPasswordChange(customer.getId(), "Password changed successfully");

        return usersMapper.toResponse(updateCustomer);


    }


    public UsersResponse changeDeliveryAddress(Principal connectedUser, ChangeAddressRequest request) throws AccountException {

        Optional<UsersModel> users =  iCustomerRepo.findByEmail(connectedUser.getName());

        if (users.isEmpty()) {
            throw new AccountException("Account is not there ");
        }


        UsersModel customer = users.get();

        customer.setAddressLine3(request.getAddressLine1());
        customer.setAddressLine2(request.getAddressLine2());
        customer.setAddressLine3(request.getAddressLine3());
        customer.setCity(request.getCity());
        customer.setCounty(request.getCounty());
        customer.setEircode(request.getEircode());
        customer.setCountry(request.getCountry());


        UsersModel updateUsersAddress = iCustomerRepo.save(customer);

        return  usersMapper.toResponse(updateUsersAddress);


    }

    public UsersResponse deleteUsers(UsersRequest usersRequest) throws Exception {
        Optional<UsersModel> userOpt = iCustomerRepo.findById(usersRequest.getCustomerId());

        if (userOpt.isEmpty()) {
            userOpt = iCustomerRepo.findByEmail(usersRequest.getEmail());
        }

        if (userOpt.isEmpty()) {
            throw new Exception("Account does not exist");
        }

        UsersModel user = userOpt.get();
        iCustomerRepo.delete(user);

        return usersMapper.toResponse(user);
    }



}
