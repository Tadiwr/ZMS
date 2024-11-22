package com.tadiwa.backend.features.cell.dto;

import lombok.Data;

@Data
public class UpdateCellDTO {
    private Long id;
    private String name;
    private Long branchId;
    private Long pollingStationId;
}
