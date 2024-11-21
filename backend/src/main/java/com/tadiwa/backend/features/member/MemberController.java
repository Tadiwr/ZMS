package com.tadiwa.backend.features.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tadiwa.backend.features.member.dto.AddMemberDTO;
import com.tadiwa.backend.features.member.dto.MemberDTO;
import com.tadiwa.backend.features.member.dto.UpdateMemberDTO;
import com.tadiwa.backend.features.member.utils.MemberValidator;
import com.tadiwa.backend.shared.exceptions.IdNumberAlreadyTaken;
import com.tadiwa.backend.shared.exceptions.NotFound;
import com.tadiwa.backend.shared.utilities.RestControllerUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/members")
public class MemberController extends RestControllerUtils {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberValidator validator;

    @GetMapping("")
    public Iterable<MemberDTO> getAll() {
        return transform(memberService.getAllMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMethodName(@PathVariable Long id) {
        return might404(
            memberService.getMemberById(id)
        );
    }
    
    @PostMapping("/add")
    @Operation(
        summary = "Add a member", description = "Adds a new member",
        responses = {
            @ApiResponse(responseCode = "404", description = "The cellId used doesn't refer to an existing cell in the cell database")
        }
    )
    
    public ResponseEntity<MemberDTO> addMember(@RequestBody AddMemberDTO dto) {
        
        try {
            validator.validateDTO(dto);
        } catch (IdNumberAlreadyTaken e) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Member member = memberService.createMember(dto);
            return ResponseEntity.ok(transform(member));

        } catch (NotFound e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<MemberDTO> updateMember(@RequestBody UpdateMemberDTO dto) {
        try {
            Member member = memberService.updateMember(dto);
            return ResponseEntity.ok(transform(member));

        } catch (NotFound e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }

}
