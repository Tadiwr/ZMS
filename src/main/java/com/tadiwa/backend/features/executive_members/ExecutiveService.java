package com.tadiwa.backend.features.executive_members;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadiwa.backend.features.cell.Cell;
import com.tadiwa.backend.features.cell.CellService;
import com.tadiwa.backend.features.member.Member;
import com.tadiwa.backend.features.member.MemberService;
import com.tadiwa.backend.features.member.dto.MemberDTO;
import com.tadiwa.backend.shared.exceptions.NotFound;

@Service
public class ExecutiveService {
    
    @Autowired
    private CellService cellService;

    @Autowired
    private MemberService memberService;


    public ExecutiveMemberDTO assignExecutiveMember(AssignRoleDTO assignRoleDTO) throws NotFound {
        Optional<Cell> cellOptional = cellService.findCellById(assignRoleDTO.cellId());

        if (cellOptional.isEmpty()) {
            throw new NotFound();
        }

        Cell cell = cellOptional.get();
        Optional<Member> memberOptional = memberService.getCellMember(cell, assignRoleDTO.memberId());

        if (memberOptional.isEmpty()) {
            throw new NotFound();
        }

        Member member = memberOptional.get();

        switch (assignRoleDTO.role()) {
        
            case POLITICAL_COMMISSAR:
                cell.setPoliticalCommissar(member);
                break;
            case TREASURER:
                cell.setTreasurer(member);
                break;
            default:
                cell.setChairPerson(member);
                break;
        }

        cellService.update(cell);

        ExecutiveMemberDTO dto = new ExecutiveMemberDTO(MemberDTO.from(member), cell.getId(), cell.getName(), assignRoleDTO.role());

        return dto;

    }

    public CellExecutiveMembersDTO getCellExecs(Long cellId) throws NotFound {
        Optional<Cell> cellOptional = cellService.findCellById(cellId);

        if (cellOptional.isPresent()) {
            Cell cell = cellOptional.get();

            return CellExecutiveMembersDTO.from(cell);
        }

        throw new NotFound();
    }


    public List<ExecutiveMemberDTO> getAllExecutives() {
        List<Cell> cells = (List<Cell>) cellService.getAllCells();
        List<ExecutiveMemberDTO> execs = new ArrayList<>();

        for (Cell cell: cells) {

            Member chair = cell.getChairPerson();
            Member pc = cell.getPoliticalCommissar();
            Member treasurer = cell.getTreasurer();

            if (chair != null) {
                execs.add(new ExecutiveMemberDTO(MemberDTO.from(chair), cell.getId(), cell.getName(), ExecutiveRole.CHAIR_PERSON));
            }

            if (pc != null) {
                execs.add(new ExecutiveMemberDTO(MemberDTO.from(pc), cell.getId(), cell.getName(), ExecutiveRole.POLITICAL_COMMISSAR));
            }

            if (treasurer != null) {
                execs.add(new ExecutiveMemberDTO(MemberDTO.from(treasurer), cell.getId(), cell.getName(), ExecutiveRole.TREASURER));
            }

        }

        
        return execs;

    }

    public CellExecutiveMembersDTO relieveRole(RelieveRoleDTO dto) throws NotFound {
        Optional<Cell> cellOptional = cellService.findCellById(dto.cellId());

        if (cellOptional.isEmpty()) {
            throw new NotFound();
        } 

        Cell cell = cellOptional.get();

        switch (dto.role()) {
        
            case POLITICAL_COMMISSAR:
                cell.setPoliticalCommissar(null);
                break;
            case TREASURER:
                cell.setTreasurer(null);
                break;
            default:
                cell.setChairPerson(null);
                break;
        }

        cellService.update(cell);

        return CellExecutiveMembersDTO.from(cell);
    }
    

}
