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
import com.tadiwa.backend.shared.exceptions.NotFound;
import com.tadiwa.backend.shared.utilities.RestControllerUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/members")
public class MemberController extends RestControllerUtils {

    @Autowired
    private MemberService memberService;


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
    public ResponseEntity<MemberDTO> addMember(@RequestBody AddMemberDTO dto) {
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
