package com.tadiwa.backend.features.branches.dto;

import java.util.List;

import com.tadiwa.backend.features.cell.Cell;

public record BranchDTO(
    Long id,
    String name,
    Long partyDistrictId,
    List<Cell> cells
) {
    
}
