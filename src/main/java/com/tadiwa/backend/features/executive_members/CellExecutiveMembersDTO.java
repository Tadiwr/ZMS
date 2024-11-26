package com.tadiwa.backend.features.executive_members;

import com.tadiwa.backend.features.cell.Cell;
import com.tadiwa.backend.features.member.dto.MemberDTO;

public record CellExecutiveMembersDTO(
    Long cellId,
    String cellName,
    MemberDTO treasurer,
    MemberDTO chairPerson,
    MemberDTO politicalCommisar

) {
    
    public static CellExecutiveMembersDTO from(Cell cell) {
        return new CellExecutiveMembersDTO(
            cell.getId(),
            cell.getName(),
            MemberDTO.from(cell.getTreasurer()),
            MemberDTO.from(cell.getChairPerson()),
            MemberDTO.from(cell.getPoliticalCommisar())
        );
    }

}
