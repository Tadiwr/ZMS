package com.tadiwa.backend.features.branches;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tadiwa.backend.features.branches.dto.AddBranchDTO;
import com.tadiwa.backend.features.branches.dto.UpdateBranchDTO;
import com.tadiwa.backend.shared.exceptions.NotFound;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;
    
    @PostMapping("/add")
    public ResponseEntity<Branch> addBranch(@RequestBody AddBranchDTO dto) {

        try {
            Branch branch = branchService.createBrach(dto);

            return ResponseEntity.ok(branch);

        } catch (NotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Branch> updateBranch(@RequestBody UpdateBranchDTO dto) {

        try {
            Branch branch = branchService.updateBrach(dto);

            return ResponseEntity.ok(branch);

        } catch (NotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public Iterable<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable Long id) {
        Optional<Branch> branchOptional = branchService.getBranchById(id);

        if (branchOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Branch branch = branchOptional.get();

        return ResponseEntity.ok(branch);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);

        return ResponseEntity.ok().build();
    }
    
    

}
