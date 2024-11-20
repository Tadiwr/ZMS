package com.tadiwa.backend.features.constituencies;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tadiwa.backend.features.constituencies.dtos.AddConstituencyDTO;
import com.tadiwa.backend.features.constituencies.dtos.UpdateConstituencyDTO;
import com.tadiwa.backend.shared.exceptions.NotFound;

@RestController
@RequestMapping("/constituencies")
public class ConstituencyController {
    @Autowired
    private ConstituencyService service;
    
    @PostMapping("/add")
    public ResponseEntity<Constituency> addConstituency(@RequestBody AddConstituencyDTO dto) {

        try {
            Constituency constituency = service.createConstituency(dto);

            return ResponseEntity.ok(constituency);

        } catch (NotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Constituency> updateConstituency(@RequestBody UpdateConstituencyDTO dto) {

        try {
            Constituency constituency = service.updateConstituency(dto);

            return ResponseEntity.ok(constituency);

        } catch (NotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public Iterable<Constituency> getAllConstituencies() {
        return service.getAllConstituencies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Constituency> getConstituencyById(@PathVariable Long id) {
        Optional<Constituency> constituencyOptional = service.getConstituencyById(id);

        if (constituencyOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Constituency constituency = constituencyOptional.get();

        return ResponseEntity.ok(constituency);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteConstituency(@PathVariable Long id) {
        service.deleteConstituency(id);

        return ResponseEntity.ok().build();
    }
}
