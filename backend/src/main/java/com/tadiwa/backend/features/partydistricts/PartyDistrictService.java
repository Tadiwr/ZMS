package com.tadiwa.backend.features.partydistricts;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadiwa.backend.features.admindistrict.AdminDistrict;
import com.tadiwa.backend.features.admindistrict.AdminDistrictService;
import com.tadiwa.backend.features.partydistricts.dto.AddPartyDistrictDTO;
import com.tadiwa.backend.features.partydistricts.dto.UpdatePartyDistrictDTO;
import com.tadiwa.backend.shared.exceptions.NotFound;

@Service
public class PartyDistrictService {
    
    @Autowired
    private PartyDistrictRepository repo;

    @Autowired
    private AdminDistrictService adminDistrictService;

    public PartyDistrict createPartyDistrict(AddPartyDistrictDTO dto) throws NotFound {
        PartyDistrict partyDistrict = new PartyDistrict();
        Optional<AdminDistrict> adminDistOptional = adminDistrictService.getAdminDistrictById(dto.getAdminDistrictId());

        if (adminDistOptional.isEmpty()) {
            throw new NotFound();
        }

        AdminDistrict adminDistrict = adminDistOptional.get();

        partyDistrict.setAdminDistrict(adminDistrict);
        partyDistrict.setName(dto.getName());

        return repo.save(partyDistrict);
    }

    public void deletePartyDistrict(Long id) {
        repo.deleteById(id);
    }

    public Iterable<PartyDistrict> getAllPartyDistricts() {
        return repo.findAll();
    }

    public Optional<PartyDistrict> getPartyDistrictById(Long id) {
        return repo.findById(id);
    }

    public PartyDistrict updatePartyDistrict(UpdatePartyDistrictDTO dto) throws NotFound {
        Optional<PartyDistrict> partyDistOptional = repo.findById(dto.getId());

        if (partyDistOptional.isEmpty()) {
            throw new NotFound();
        }

        PartyDistrict partyDistrict = partyDistOptional.get();
        Optional<AdminDistrict> adminDistOptional = adminDistrictService.getAdminDistrictById(dto.getAdminDistrictId());

        if (adminDistOptional.isEmpty()) {
            throw new NotFound();
        }

        AdminDistrict adminDistrict = adminDistOptional.get();

        partyDistrict.setAdminDistrict(adminDistrict);
        partyDistrict.setName(dto.getName());

        return repo.save(partyDistrict);
    }

}
