package com.tadiwa.backend.features.member.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadiwa.backend.features.member.MemberRepository;
import com.tadiwa.backend.features.member.dto.AddMemberDTO;
import com.tadiwa.backend.shared.exceptions.IdNumberAlreadyTaken;

@Service
public class MemberValidator {
    
    @Autowired
    private MemberRepository memberRepository;


    public void validateDTO(AddMemberDTO dto) throws IdNumberAlreadyTaken {
        if (!validateIdNumberUniqueness(dto.idNumber())) {
            throw new IdNumberAlreadyTaken();
        }
    }

    private boolean validateIdNumberUniqueness(String idNumber) {
        Long count = memberRepository.countByIdNumber(idNumber);

        return count == 0;
    }

}
