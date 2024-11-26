package com.tadiwa.backend.features.executive_members;


import com.tadiwa.backend.features.member.dto.MemberDTO;

public record ExecutiveMemberDTO(
    MemberDTO member,
    Long cellId,
    String cellName,
    ExecutiveRole role
) {
    
}
