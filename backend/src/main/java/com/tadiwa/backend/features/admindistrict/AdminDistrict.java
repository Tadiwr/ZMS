package com.tadiwa.backend.features.admindistrict;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tadiwa.backend.features.provinces.Province;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "admindistricts")
public class AdminDistrict {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "province_id")
    private Province province;
    

}