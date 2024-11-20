package com.tadiwa.backend.features.partydistricts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tadiwa.backend.features.admindistrict.AdminDistrict;

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
@Table(name = "party_districts")
public class PartyDistrict {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "admin_district_id")
    private AdminDistrict adminDistrict;
    
}
