package com.tadiwa.backend.features.partydistricts.dto;

import lombok.Data;

@Data
public class UpdatePartyDistrictDTO {
    private Long id;
    private String name;
    private Long adminDistrictId;
}
