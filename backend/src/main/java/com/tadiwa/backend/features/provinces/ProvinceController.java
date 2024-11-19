package com.tadiwa.backend.features.provinces;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tadiwa.backend.features.provinces.dto.AddProvinceDTO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("provinces")
public class ProvinceController {

    @Autowired
    private ProvinceService provService;
    
    @PostMapping("/add")
    public ResponseEntity<Province> addProvince(@RequestBody AddProvinceDTO dto) {
        Province province = provService.createProvince(dto);

        return ResponseEntity.ok(province);
    }

    @GetMapping("")
    public List<Province> allProvinces() {
        return provService.getAllProvinces();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Province> getById(@PathVariable Long id) {
        Optional<Province> provOptional = provService.getProvinceById(id);

        if (provOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Province province = provOptional.get();

        return ResponseEntity.ok(province);
    }
    
    

}
