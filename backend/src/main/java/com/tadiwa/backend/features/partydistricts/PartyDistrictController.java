package com.tadiwa.backend.features.partydistricts;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tadiwa.backend.features.admindistrict.AdminDistrict;
import com.tadiwa.backend.features.partydistricts.dto.AddPartyDistrictDTO;
import com.tadiwa.backend.features.partydistricts.dto.UpdatePartyDistrictDTO;
import com.tadiwa.backend.shared.exceptions.NotFound;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/party-districts")
public class PartyDistrictController {
    
    @Autowired
    private PartyDistrictService partyDistrictService;

    @GetMapping("")
    public Iterable<PartyDistrict> getAll() {
        return partyDistrictService.getAllPartyDistricts();
    }

    @PostMapping("/add")
    public ResponseEntity<PartyDistrict> add(@RequestBody AddPartyDistrictDTO dto) {
        
        try {
            PartyDistrict partyDistrict = partyDistrictService.createPartyDistrict(dto);
            return ResponseEntity.ok(partyDistrict);
        } catch(NotFound err) {
            return ResponseEntity.notFound().build();
        }
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartyDistrict> getAdminDistrict(@PathVariable Long id) {

        Optional<PartyDistrict> optional = partyDistrictService.getPartyDistrictById(id);

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PartyDistrict adminDistrict = optional.get();

        return ResponseEntity.ok(adminDistrict);
        
    }
    
    @PutMapping("/update")
    public ResponseEntity<PartyDistrict> add(@RequestBody UpdatePartyDistrictDTO dto) {
        
        try {
            PartyDistrict partyDistrict = partyDistrictService.updatePartyDistrict(dto);
            return ResponseEntity.ok(partyDistrict);
        } catch(NotFound err) {
            return ResponseEntity.notFound().build();
        }
        
    }
    

}
