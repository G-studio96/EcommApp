package com.lauder.app.ecommapp.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles;

    public Role() {

    }

    public enum  Roles {
        ROLE_ADMIN,
        ROLE_PROMOTER,
        ROLE_CUSTOMER,
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}
