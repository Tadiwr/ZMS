package com.tadiwa.backend.features.branches.dto;

import lombok.Data;

@Data
public class AddBranchDTO {
    private String name;
    private Long partyDistrictId;
}
