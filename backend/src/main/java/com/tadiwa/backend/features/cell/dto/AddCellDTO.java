package com.tadiwa.backend.features.cell.dto;

import lombok.Data;

@Data
public class AddCellDTO {
    private String name;
    private Long branchId;
    private Long pollingStationId;
}
