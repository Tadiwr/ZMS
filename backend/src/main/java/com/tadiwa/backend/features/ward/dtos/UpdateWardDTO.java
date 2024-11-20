package com.tadiwa.backend.features.ward.dtos;

import lombok.Data;

@Data
public class UpdateWardDTO {
    private Long id;
    private String name;
    private Long constituencyId;
}
