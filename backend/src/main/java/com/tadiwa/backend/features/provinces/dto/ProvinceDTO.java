package com.tadiwa.backend.features.provinces.dto;

import java.util.List;

import com.tadiwa.backend.features.admindistrict.AdminDistrict;
import com.tadiwa.backend.features.constituencies.Constituency;

public record ProvinceDTO(
    Long id,
    String name,
    List<AdminDistrict> adminDistricts,
    List<Constituency> constituencies
) {}
