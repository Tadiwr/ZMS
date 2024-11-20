package com.tadiwa.backend.features.admindistrict;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tadiwa.backend.features.admindistrict.dtos.CreateAdminDistrictDTO;
import com.tadiwa.backend.features.admindistrict.dtos.UpdateAdminDistrictDTO;
import com.tadiwa.backend.shared.exceptions.NotFound;
import com.tadiwa.backend.shared.exceptions.ProvinceNotFound;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAdminDistrict(@PathVariable Long id) {
        adminDistService.deleteAdminDistrict(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<Iterable<AdminDistrict>> all() {

        Iterable<AdminDistrict> adminDistricts = adminDistService.getAllAdminDistricts();
        return ResponseEntity.ok(adminDistricts);
        
    }

    @PutMapping("/update")
    public ResponseEntity<AdminDistrict> add(@RequestBody UpdateAdminDistrictDTO dto) {
        
        try {
            AdminDistrict district = adminDistService.updateAdminDistrict(dto);
            return ResponseEntity.ok(district);

        } catch (NotFound err) {
            err.printStackTrace();
            return ResponseEntity.notFound().build();
        }   
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDistrict> getAdminDistrict(@PathVariable Long id) {

        Optional<AdminDistrict> optional = adminDistService.getAdminDistrictById(id);

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AdminDistrict adminDistrict = optional.get();

        return ResponseEntity.ok(adminDistrict);
        
    }

    

}
