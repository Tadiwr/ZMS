package com.tadiwa.backend.features.admindistrict;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tadiwa.backend.features.admindistrict.dtos.AdminDistrictDTO;
import com.tadiwa.backend.features.partydistricts.PartyDistrict;
import com.tadiwa.backend.features.provinces.Province;
import com.tadiwa.backend.shared.tranferable.Transferable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "admindistricts")
public class AdminDistrict implements Transferable<AdminDistrictDTO> {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "province_id")
    private Province province;
    
    @OneToMany(mappedBy = "adminDistrict")
    private List<PartyDistrict> partyDistricts;

    public AdminDistrictDTO toDTO() {
        return new AdminDistrictDTO(
            this.id,
            this.name,
            this.getProvince().getId(),
            this.partyDistricts
        );
    }


}