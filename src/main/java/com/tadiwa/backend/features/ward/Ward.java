package com.tadiwa.backend.features.ward;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tadiwa.backend.features.constituencies.Constituency;
import com.tadiwa.backend.features.pollingstation.PollingStation;
import com.tadiwa.backend.features.ward.dtos.WardDTO;
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
@Table(name = "wards")
public class Ward implements Transferable<WardDTO> {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "constituency_id")
    @JsonIgnore
    private Constituency constituency;


    @OneToMany(mappedBy = "ward", cascade = CascadeType.REMOVE)
    private List<PollingStation> pollingStations;

    @Override
    public WardDTO toDTO() {
        return new WardDTO(
            this.getId(),
            this.getName(),
            this.getConstituency().getId(),
            this.getPollingStations()
        );
    }



}
