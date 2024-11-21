package com.tadiwa.backend.features.cell.dto;

public record CellDTO(
    Long id,
    String name,
    Long branchId,
    Long pollingStationId
) {
    
}
