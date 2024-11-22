package com.tadiwa.backend.features.cell.dto;

import java.util.List;

import com.tadiwa.backend.features.member.Member;

public record CellDTO(
    Long id,
    String name,
    Long branchId,
    Long pollingStationId,
    List<Member> members
) {
    
}
