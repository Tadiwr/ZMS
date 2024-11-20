package com.tadiwa.backend.features.admindistrict.dtos;

import lombok.Data;

@Data
public class CreateAdminDistrictDTO {
    
    private String name;
    private Long provinceId;

}
