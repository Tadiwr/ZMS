package com.tadiwa.backend.features.executive_members;


import com.tadiwa.backend.features.cell.Cell;
import com.tadiwa.backend.features.member.Member;

public record ExecutiveMemberDTO(
    Member member,
    Cell cell,
    ExecutiveRole role
) {
    
}
