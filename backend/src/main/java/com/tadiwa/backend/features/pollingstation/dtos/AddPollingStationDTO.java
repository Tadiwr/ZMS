package com.tadiwa.backend.features.pollingstation.dtos;

import lombok.Data;

@Data
public class AddPollingStationDTO {
    private String name;
    private Long wardId;
}
