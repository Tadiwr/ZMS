package com.tadiwa.backend.features.admindistrict.dtos;

import java.util.List;

import com.tadiwa.backend.features.partydistricts.PartyDistrict;

public record AdminDistrictDTO(
    Long id,
    String name,
    Long provinceId,
    List<PartyDistrict> partyDistricts
) {
    
}
