package com.tadiwa.backend.features.branches.dto;

import lombok.Data;

@Data
public class UpdateBranchDTO {
    private Long id;
    private String name;
    private Long partyDistrictId;
}
