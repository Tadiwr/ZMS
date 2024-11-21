package com.tadiwa.backend.features.pollingstation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tadiwa.backend.features.pollingstation.dtos.PollingStationDTO;
import com.tadiwa.backend.features.ward.Ward;
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
@Table(name = "polling_stations")
public class PollingStation implements Transferable<PollingStationDTO> {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    @JsonIgnore
    private Ward ward;

    @Override
    public PollingStationDTO toDTO() {
        return new PollingStationDTO(
            this.id,
            this.name,
            this.getWard().getId()
        );
    }
    
}
