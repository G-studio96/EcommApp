package com.lauder.app.ecommapp.repo;


import com.lauder.app.ecommapp.dto.request.users.UsersRequest;
import com.lauder.app.ecommapp.model.Role;
import com.lauder.app.ecommapp.model.UsersModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface IUsersRepo extends JpaRepository<UsersModel, Long> {

    Optional<UsersModel> findCustomerModelById(Long id);

    Optional<UsersModel> findByEmail(String email);

    Optional<UsersModel> findByPhoneNumber(String phoneNumber);

    Optional<UsersModel> searchByCountry(String Country);

    Optional<UsersModel> searchByEircode(String Eircode);

    @Override
    Page<UsersModel> findAll(Pageable pageable);

    long countAllByCountry(String country);

    long countAllByCountryAndCity(String country, String city);


    void delete(UsersRequest usersRequest);

    @Query("SELECT users FROM UsersModel users WHERE users.role IN :role")
    Optional<UsersModel> findAllByRole(@Param("customer") Set<Role> role);

    @Query("SELECT COUNT(u) FROM UsersModel u WHERE U.id = :id")
    long countAllCustomer(@Param("id") Long id);


}
