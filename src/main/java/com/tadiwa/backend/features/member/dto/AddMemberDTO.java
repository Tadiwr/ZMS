package com.tadiwa.backend.features.member.dto;

import java.util.Date;

public record AddMemberDTO(
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
