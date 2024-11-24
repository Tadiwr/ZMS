package com.tadiwa.backend.features.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "users")
@Entity
@Data
public class User {
    
    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String password;

}
