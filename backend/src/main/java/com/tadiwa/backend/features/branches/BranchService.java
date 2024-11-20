package com.tadiwa.backend.features.branches;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadiwa.backend.features.branches.dto.AddBranchDTO;
import com.tadiwa.backend.features.partydistricts.PartyDistrict;
import com.tadiwa.backend.features.partydistricts.PartyDistrictService;
import com.tadiwa.backend.shared.exceptions.NotFound;

@Service
public class BranchService {
    
    @Autowired
    private BranchRepository branchRepo; 

    @Autowired
    private PartyDistrictService partyDistService;

    public Branch createBrach(AddBranchDTO dto) throws NotFound {
        Branch branch = new Branch();

        Optional<PartyDistrict> partyOptional = partyDistService.getPartyDistrictById(dto.getPartyDistrictId());

        if (partyOptional.isEmpty()) {
            throw new NotFound();
        }

        PartyDistrict partyDistrict = partyOptional.get();

        branch.setName(dto.getName());
        branch.setPartyDistrict(partyDistrict);
        
        return branchRepo.save(branch);
    }

    public Optional<Branch> getBranchById(Long id) {
        return branchRepo.findById(id);
    }

    public Iterable<Branch> getAllBranches() {
        return branchRepo.findAll();
    }

    public Branch updateBrach(AddBranchDTO dto) throws NotFound {
        Branch branch = new Branch();

        Optional<PartyDistrict> partyOptional = partyDistService.getPartyDistrictById(dto.getPartyDistrictId());

        if (partyOptional.isEmpty()) {
            throw new NotFound();
        }

        PartyDistrict partyDistrict = partyOptional.get();

        branch.setName(dto.getName());
        branch.setPartyDistrict(partyDistrict);
        
        return branchRepo.save(branch);
    }

}
