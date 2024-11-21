package com.tadiwa.backend.features.pollingstation.dtos;

import java.util.List;

import com.tadiwa.backend.features.cell.Cell;

public record PollingStationDTO(
    Long id,
    String name,
    Long wardId,
    List<Cell> cells
) {
    
}
