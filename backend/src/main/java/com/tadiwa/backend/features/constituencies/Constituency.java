package com.tadiwa.backend.features.constituencies;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tadiwa.backend.features.constituencies.dtos.ConstituencyDTO;
import com.tadiwa.backend.features.provinces.Province;
import com.tadiwa.backend.features.ward.Ward;
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
@Table(name = "contituencies")

public class Constituency implements Transferable<ConstituencyDTO> {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "province_id")
    private Province province;

    @OneToMany(mappedBy = "constituency", cascade = CascadeType.REMOVE)
    private List<Ward> wards;

    @Override
    public ConstituencyDTO toDTO() {
        return new ConstituencyDTO(
            this.getId(),
            this.getName(),
            this.getWards(),
            this.getProvince().getId()
            );
    }

}
