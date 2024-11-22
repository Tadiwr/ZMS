package com.tadiwa.backend.features.pollingstation.dtos;

import lombok.Data;

@Data
public class UpdatePollingStationDTO {
    private Long id;
    private String name;
    private Long wardId;
}
