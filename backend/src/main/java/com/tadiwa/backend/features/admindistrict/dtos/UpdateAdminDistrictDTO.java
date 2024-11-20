package com.tadiwa.backend.features.admindistrict.dtos;

import lombok.Data;

@Data
public class UpdateAdminDistrictDTO {
    
    private Long id;
    private String name;
    private Long provinceId;

}
