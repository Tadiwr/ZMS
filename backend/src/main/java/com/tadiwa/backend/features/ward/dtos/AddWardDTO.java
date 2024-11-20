package com.tadiwa.backend.features.ward.dtos;

import lombok.Data;

@Data
public class AddWardDTO {
    private String name;
    private Long constituencyId;
}
