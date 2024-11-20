package com.tadiwa.backend.features.ward;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadiwa.backend.features.constituencies.Constituency;
import com.tadiwa.backend.features.constituencies.ConstituencyService;
import com.tadiwa.backend.features.ward.dtos.AddWardDTO;
import com.tadiwa.backend.features.ward.dtos.UpdateWardDTO;
import com.tadiwa.backend.shared.exceptions.NotFound;

@Service
public class WardService {
    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private ConstituencyService constituencyService;


    public Ward createWard(AddWardDTO dto) throws NotFound {
        Optional<Constituency> constituencyOptional = constituencyService.getConstituencyById(dto.getConstituencyId());

        if (constituencyOptional.isEmpty()) {
            throw new NotFound();
        }

        Constituency constituency = constituencyOptional.get();
        Ward ward = new Ward();

        ward.setName(dto.getName());
        ward.setConstituency(constituency);

        return wardRepository.save(ward);

    }

    public Ward updateWard(UpdateWardDTO dto) throws NotFound {
        Optional<Constituency> constituencyOptional = constituencyService.getConstituencyById(dto.getConstituencyId());
        Optional<Ward> wardOptional = wardRepository.findById(dto.getId());

        if (wardOptional.isEmpty() || constituencyOptional.isEmpty()) {
            throw new NotFound();
        }

        Constituency constituency = constituencyOptional.get();
        Ward ward = wardOptional.get();

        ward.setName(dto.getName());
        ward.setConstituency(constituency);

        return wardRepository.save(ward);

    }

    public Optional<Ward> getWardById(Long id) {
        return wardRepository.findById(id);
    }

    public Iterable<Ward> getAllWards() {
        return wardRepository.findAll();
    }

    public void deleteWard(Long id) {
        wardRepository.deleteById(id);
    }
}
