package com.tadiwa.backend.features.constituencies.dtos;

import lombok.Data;

@Data
public class UpdateConstituencyDTO {
    private Long id;
    private String name;
    private Long provinceId;
}
