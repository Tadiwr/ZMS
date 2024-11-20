package com.tadiwa.backend.features.provinces;

import jakarta.persistence.Table;

import java.util.List;

import com.tadiwa.backend.features.admindistrict.AdminDistrict;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "province")
    private List<AdminDistrict> adminDistricts;

}