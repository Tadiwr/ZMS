package com.tadiwa.backend.features.cell;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tadiwa.backend.features.branches.Branch;
import com.tadiwa.backend.features.cell.dto.CellDTO;
import com.tadiwa.backend.features.pollingstation.PollingStation;
import com.tadiwa.backend.shared.tranferable.Transferable;

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
@Table(name = "cells")
public class Cell implements Transferable<CellDTO>{
    
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "polling_station_id")
    private PollingStation pollingStation;

    @Override
    public CellDTO toDTO() {
        return new CellDTO(
            this.id,
            this.name,
            this.getBranch().getId(),
            this.getPollingStation().getId()
        );
    }

}