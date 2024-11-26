package com.tadiwa.backend.features.cell;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tadiwa.backend.features.branches.Branch;
import com.tadiwa.backend.features.cell.dto.CellDTO;
import com.tadiwa.backend.features.member.Member;
import com.tadiwa.backend.features.pollingstation.PollingStation;
import com.tadiwa.backend.shared.tranferable.Transferable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cells")
public class Cell implements Transferable<CellDTO>{
    
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToOne
    private Member politicalCommisar = null;

    @OneToOne
    private Member chairPerson = null;

    @OneToOne
    private Member treasurer = null;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "polling_station_id")
    private PollingStation pollingStation;

    @OneToMany(mappedBy = "cell", cascade = CascadeType.REMOVE)
    private List<Member> members;

    @Override
    public CellDTO toDTO() {
        return new CellDTO(
            this.id,
            this.name,
            this.getBranch().getId(),
            this.getPollingStation().getId(),
            this.getMembers()
        );
    }

}
