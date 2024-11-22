package com.tadiwa.backend.features.member;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadiwa.backend.features.cell.Cell;
import com.tadiwa.backend.features.cell.CellService;
import com.tadiwa.backend.features.member.dto.AddMemberDTO;
import com.tadiwa.backend.features.member.dto.UpdateMemberDTO;
import com.tadiwa.backend.shared.exceptions.NotFound;

@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepo;

    @Autowired
    private CellService cellService;

 
    public Iterable<Member> getAllMembers() {
        return memberRepo.findAll();
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepo.findById(id);
    }

    public Member createMember(AddMemberDTO dto) throws NotFound {
        Optional<Cell> cellOptional = cellService.findCellById(dto.cellID());

        if (cellOptional.isEmpty()) {
            throw new NotFound();
        }

        Cell cell = cellOptional.get();

        Member member = new Member();

        member.setCell(cell);
        member.setCreatedAt(System.currentTimeMillis());
        member.setDateOfBirth(dto.dateOfBirth());
        member.setFullName(dto.fullName());
        member.setIdNumber(dto.idNumber());
        member.setMembershipNumber(dto.membershipNumber());
        member.setPhoneNumber(dto.phoneNumber());
        member.setPhysicalAddress(dto.physicalAddress());
        member.setVoterRegNumber(dto.voterRegNumber());

        return memberRepo.save(member);
    }

    public Member updateMember(UpdateMemberDTO dto) throws NotFound {
        Optional<Cell> cellOptional = cellService.findCellById(dto.cellID());
        Optional<Member> memberOptional = memberRepo.findById(dto.id());
        
        if (cellOptional.isEmpty() || memberOptional.isEmpty()) {
            throw new NotFound();
        }

        Cell cell = cellOptional.get();

        Member member = memberOptional.get();

        member.setCell(cell);
        member.setCreatedAt(System.currentTimeMillis());
        member.setDateOfBirth(dto.dateOfBirth());
        member.setFullName(dto.fullName());
        member.setIdNumber(dto.idNumber());
        member.setMembershipNumber(dto.membershipNumber());
        member.setPhoneNumber(dto.phoneNumber());
        member.setPhysicalAddress(dto.physicalAddress());
        member.setVoterRegNumber(dto.voterRegNumber());

        return memberRepo.save(member);
    }

    public void deleteMember(Long id) {
        memberRepo.deleteById(id);
    }
}
