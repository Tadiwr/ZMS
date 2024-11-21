package com.tadiwa.backend.features.partydistricts;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tadiwa.backend.features.admindistrict.AdminDistrict;
import com.tadiwa.backend.features.branches.Branch;
import com.tadiwa.backend.features.partydistricts.dto.PartyDistrictDTO;
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
@Table(name = "party_districts")
public class PartyDistrict implements Transferable<PartyDistrictDTO> {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "admin_district_id")
    private AdminDistrict adminDistrict;

    @OneToMany(mappedBy = "partyDistrict")
    private List<Branch> branchs;

    @Override
    public PartyDistrictDTO toDTO() {
        return new PartyDistrictDTO(
            this.id,
            this.name,
            this.getAdminDistrict().getId(),
            this.branchs
        );
    }

    
    
}
