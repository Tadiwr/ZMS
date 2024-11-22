package com.tadiwa.backend.features.branches;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tadiwa.backend.features.branches.dto.BranchDTO;
import com.tadiwa.backend.features.cell.Cell;
import com.tadiwa.backend.features.partydistricts.PartyDistrict;
import com.tadiwa.backend.shared.tranferable.Transferable;

import jakarta.persistence.CascadeType;
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
@Table(name = "branches")
public class Branch implements Transferable<BranchDTO> {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "party_district_id")
    private PartyDistrict partyDistrict;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.REMOVE)
    private List<Cell> cells;


    @Override
    public BranchDTO toDTO() {
        return new BranchDTO(
            this.id,
            this.name,
            this.getPartyDistrict().getId(),
            this.getCells()
        );
    }


}
