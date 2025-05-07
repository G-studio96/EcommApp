package com.lauder.app.ecommapp.util;

import com.lauder.app.ecommapp.model.Role;
import com.lauder.app.ecommapp.model.UsersModel;
import com.lauder.app.ecommapp.repo.IUsersRepo;
import com.lauder.app.ecommapp.repo.IRoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    final IRoleRepo rolesRepo;

    final IUsersRepo usersRepo;

    final PasswordEncryption passwordEncryption;

    public DataInitializer(IRoleRepo rolesRepo, IUsersRepo usersRepo, PasswordEncryption passwordEncryption) {
        this.rolesRepo = rolesRepo;
        this.usersRepo = usersRepo;
        this.passwordEncryption = passwordEncryption;

    }

    @Override
    public void run(String... args) throws Exception {
        for (Role.Roles roleName : Role.Roles.values()) {
            rolesRepo.findByRoles(Collections.singleton(roleName))
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setRoles(Collections.singleton(roleName));
                        return rolesRepo.save(role);
                    });

            Set<Role.Roles> roleAdmin = Collections.singleton(Role.Roles.ROLE_ADMIN);

            String adminEmail = "admin@lauder.com";
            String adminPassword = "password@123";


            if (usersRepo.findByEmail(adminEmail).isEmpty()) {
                Set<Role> adminRole = Set.of(rolesRepo.findByRoles(roleAdmin)
                        .orElseThrow(() -> new RuntimeException("Admin role not found")));

                UsersModel admin = new UsersModel();
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncryption.encode(adminPassword));
                admin.setRole(adminRole);

                usersRepo.save(admin);
            }
        }
    }
}

