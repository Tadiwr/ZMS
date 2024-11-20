package com.tadiwa.backend.features.constituencies.dtos;

import lombok.Data;

@Data
public class AddConstituencyDTO {
    private String name;
    private Long provinceId;
}