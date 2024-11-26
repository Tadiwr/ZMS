package com.tadiwa.backend.features.executive_members;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tadiwa.backend.shared.exceptions.NotFound;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("executives")
public class ExecutiveController {

    @Autowired
    private ExecutiveService executiveService;
    
    @GetMapping("/{cellId}")
    public ResponseEntity<CellExecutiveMembersDTO> getCellExecs(@PathVariable Long cellId) {

        try {
            CellExecutiveMembersDTO cellExecutives = executiveService.getCellExecs(cellId);

            return ResponseEntity.ok(cellExecutives);
        } catch (NotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/assign")
    public ResponseEntity<ExecutiveMemberDTO> setExecutive(@RequestBody AssignRoleDTO dto) {
        try {
            ExecutiveMemberDTO executiveMember = executiveService.assignExecutiveMember(dto);
            return ResponseEntity.ok(executiveMember);
        } catch (NotFound e) {
            // e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/relieve")
    public ResponseEntity<CellExecutiveMembersDTO> relieveRole(@RequestBody RelieveRoleDTO dto) {
        try {
            CellExecutiveMembersDTO cellExecutives = executiveService.relieveRole(dto);

            return ResponseEntity.ok(cellExecutives);
        } catch (NotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public List<ExecutiveMemberDTO> allExecs() {
        return executiveService.getAllExecutives();
    }
    
    
    

}
