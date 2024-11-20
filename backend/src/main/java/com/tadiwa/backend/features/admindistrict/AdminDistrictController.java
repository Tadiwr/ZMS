package com.tadiwa.backend.features.admindistrict;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tadiwa.backend.exceptions.ProvinceNotFound;
import com.tadiwa.backend.features.admindistrict.dtos.CreateAdminDistrictDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/admin-districts")
public class AdminDistrictController {

    @Autowired
    private AdminDistrictService adminDistService;
    
    @PostMapping("/add")
    public ResponseEntity<AdminDistrict> add(@RequestBody CreateAdminDistrictDTO dto) {
        
        try {
            AdminDistrict district = adminDistService.createAdminDistrict(dto);
            return ResponseEntity.ok(district);

        } catch (ProvinceNotFound err) {
            err.printStackTrace();
            return ResponseEntity.notFound().build();
        }   
        
    }
    

}
