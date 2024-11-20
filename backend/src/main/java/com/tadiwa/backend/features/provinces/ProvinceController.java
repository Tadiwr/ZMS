package com.tadiwa.backend.features.provinces;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tadiwa.backend.features.provinces.dto.AddProvinceDTO;
import com.tadiwa.backend.features.provinces.dto.UpdateProvinceDTO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@RequestMapping("provinces")
@CrossOrigin(origins="*")
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
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {

        try {
            provService.deleteProvince(id);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Province> updateProvince(@RequestBody UpdateProvinceDTO dto) {
        Optional<Province> provOptional = provService.updateProvince(dto);

        if (provOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Province province = provOptional.get();

        return ResponseEntity.ok(province);

    }

    

}
