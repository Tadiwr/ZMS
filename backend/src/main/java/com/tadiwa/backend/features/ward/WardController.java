package com.tadiwa.backend.features.ward;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tadiwa.backend.features.ward.dtos.AddWardDTO;
import com.tadiwa.backend.features.ward.dtos.UpdateWardDTO;
import com.tadiwa.backend.shared.exceptions.NotFound;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/wards")
public class WardController {
    
    @Autowired
    private WardService wardService;

    @PostMapping("/add")
    public ResponseEntity<Ward> addWard(@RequestBody AddWardDTO dto) {
        try {
            Ward ward = wardService.createWard(dto);
            return ResponseEntity.ok(ward);
        } catch (NotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Ward> updateWard(@RequestBody UpdateWardDTO dto) {
        try {
            Ward ward = wardService.updateWard(dto);
            return ResponseEntity.ok(ward);
        } catch (NotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public Iterable<Ward> getAllWards() {
        return wardService.getAllWards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ward> getWardById(@PathVariable Long id) {
        Optional<Ward> wardOptional = wardService.getWardById(id);

        if (wardOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Ward ward = wardOptional.get();

        return ResponseEntity.ok(ward);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWard(@PathVariable Long id) {
        wardService.deleteWard(id);

        return ResponseEntity.ok().build();
    }
    
    
    
}
