package com.tadiwa.backend.features.provinces;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@Table(name="provinces")
public class Province {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

}