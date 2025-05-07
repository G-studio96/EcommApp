package com.lauder.app.ecommapp.service;


import com.lauder.app.ecommapp.model.UsersModel;
import com.lauder.app.ecommapp.repo.IUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUsersRepo customerRepo;

    @Autowired
    public UserDetailsServiceImpl(IUsersRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersModel user = customerRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not not found"));

        String[] roles = user.getRole().stream().map((role -> role.getRoles())).toArray(String[]::new);

        return User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }
}
