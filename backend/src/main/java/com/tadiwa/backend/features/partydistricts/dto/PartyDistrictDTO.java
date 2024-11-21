package com.tadiwa.backend.features.partydistricts.dto;

import java.util.List;

import com.tadiwa.backend.features.branches.Branch;

public record PartyDistrictDTO(
    Long id,
    String name,
    Long adminDistrictId,
    List<Branch> branchs
) {
    
}
