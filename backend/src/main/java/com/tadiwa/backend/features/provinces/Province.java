package com.tadiwa.backend.features.provinces;

import jakarta.persistence.Table;

import java.util.List;

import com.tadiwa.backend.features.admindistrict.AdminDistrict;
import com.tadiwa.backend.features.constituencies.Constituency;
import com.tadiwa.backend.features.provinces.dto.ProvinceDTO;
import com.tadiwa.backend.shared.tranferable.Transferable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
@Table(name="provinces")
public class Province implements Transferable<ProvinceDTO> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "province")
    private List<AdminDistrict> adminDistricts;

    @OneToMany(mappedBy = "province")
    private List<Constituency> constituencies;

    public ProvinceDTO toDTO() {
        return new ProvinceDTO(
            this.id,
            this.name,
            this.adminDistricts,
            this.constituencies
        );
    }

}
