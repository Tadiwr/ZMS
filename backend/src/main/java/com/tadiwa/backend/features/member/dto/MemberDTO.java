package com.tadiwa.backend.features.member.dto;

import java.time.LocalDateTime;
import java.util.Date;

public record MemberDTO(
    Long id,
    String fullName,
    Date dateOfBirth,
    Long voterRegNumber,
    String idNumber,
    String physicalAddress,
    String phoneNumber,
    Long membershipNumber,
    LocalDateTime createdAt,
    Long cellID
) {
    
}
