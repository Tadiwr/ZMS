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
import com.tadiwa.backend.shared.exceptions.NotFound;

@Service
public class ExecutiveService {
    
    @Autowired
    private CellService cellService;

    @Autowired
    private MemberService memberService;


    public ExecutiveMemberDTO addExecutiveMember(AssignRoleDTO assignRoleDTO) throws NotFound {
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
        
            case POLITICAL_COMMISAR:
                cell.setPoliticalCommisar(member);
            case TREASURER:
                cell.setTreasurer(member);
            case CHAIR_PERSON:
                cell.setChairPerson(member);
            default:
                break;
        }

        cellService.update(cell);

        ExecutiveMemberDTO dto = new ExecutiveMemberDTO(member, cell, assignRoleDTO.role());

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
            Member pc = cell.getPoliticalCommisar();
            Member treasurer = cell.getTreasurer();

            if (chair != null) {
                execs.add(new ExecutiveMemberDTO(chair, cell, ExecutiveRole.CHAIR_PERSON));
            }

            if (pc != null) {
                execs.add(new ExecutiveMemberDTO(pc, cell, ExecutiveRole.POLITICAL_COMMISAR));
            }

            if (treasurer != null) {
                execs.add(new ExecutiveMemberDTO(treasurer, cell, ExecutiveRole.TREASURER));
            }

        }

        
        return execs;

    }

}
