package com.tadiwa.backend.features.executive_members;

public record AssignRoleDTO(
    Long memberId,
    Long cellId,
    ExecutiveRole role
) {
    
}
