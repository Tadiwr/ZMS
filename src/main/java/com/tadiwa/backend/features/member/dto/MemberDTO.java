package com.tadiwa.backend.features.member.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.tadiwa.backend.features.member.Member;

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

    public static MemberDTO from(Member member) {

        if (member == null) {
            return null;
        }
            
        return new MemberDTO(
            member.getId(),
            member.getFullName(),
            member.getDateOfBirth(),
            member.getVoterRegNumber(),
            member.getIdNumber(),
            member.getPhysicalAddress(),
            member.getPhoneNumber(),
            member.getMembershipNumber(),
            member.getCreatedAt(),
            member.getCell().getId()
        );

    }
    
}
