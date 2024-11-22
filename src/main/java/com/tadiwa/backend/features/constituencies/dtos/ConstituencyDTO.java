package com.tadiwa.backend.features.constituencies.dtos;

import java.util.List;

import com.tadiwa.backend.features.ward.Ward;

public record ConstituencyDTO(
    Long id,
    String name,
    List<Ward> wards,
    Long provinceId
) {
    
}
