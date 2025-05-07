package com.lauder.app.ecommapp.repo;

import com.lauder.app.ecommapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface IRoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByRoles(Set<Role.Roles> roles);

    @Override
    Optional<Role> findById(Long id);


}
