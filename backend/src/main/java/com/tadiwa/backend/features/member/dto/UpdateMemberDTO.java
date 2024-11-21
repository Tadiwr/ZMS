package com.tadiwa.backend.features.member.dto;

import java.util.Date;

public record UpdateMemberDTO(
    Long id,
    String fullName,
    Date dateOfBirth,
    Long voterRegNumber,
    String idNumber,
    String physicalAddress,
    String phoneNumber,
    Long membershipNumber,
    Long cellID
) {
    
}
