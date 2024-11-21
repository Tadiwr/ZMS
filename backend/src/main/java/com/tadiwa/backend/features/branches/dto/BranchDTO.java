package com.tadiwa.backend.features.branches.dto;

public record BranchDTO(
    Long id,
    String name,
    Long partyDistrictId
) {
    
}
