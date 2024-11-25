package com.tadiwa.backend.features.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "users")
@Entity
@Data
public class User implements UserDetails {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }
    @Override
    public String getUsername() {
        return this.email;
    }

}
