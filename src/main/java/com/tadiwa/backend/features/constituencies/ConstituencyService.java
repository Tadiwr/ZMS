package com.tadiwa.backend.features.constituencies;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadiwa.backend.features.constituencies.dtos.AddConstituencyDTO;
import com.tadiwa.backend.features.constituencies.dtos.UpdateConstituencyDTO;
import com.tadiwa.backend.features.provinces.Province;
import com.tadiwa.backend.features.provinces.ProvinceService;
import com.tadiwa.backend.shared.exceptions.NotFound;

@Service
public class ConstituencyService {
    
    @Autowired
    private ConstituencyRepository repo;

    @Autowired
    private ProvinceService provinceService;


    public Constituency createConstituency(AddConstituencyDTO dto) throws NotFound {
        Optional<Province> provOptional = provinceService.getProvinceById(dto.getProvinceId());

        if (provOptional.isEmpty()) {
            throw new NotFound();
        }

        Province province = provOptional.get();
        Constituency constituency = new Constituency();

        constituency.setName(dto.getName());
        constituency.setProvince(province);

        return repo.save(constituency);

    }

    public Constituency updateConstituency(UpdateConstituencyDTO dto) throws NotFound {
        Optional<Province> provOptional = provinceService.getProvinceById(dto.getProvinceId());
        Optional<Constituency> constOptional = repo.findById(dto.getId());

        if (provOptional.isEmpty() || constOptional.isEmpty()) {
            throw new NotFound();
        }

        Province province = provOptional.get();
        Constituency constituency = constOptional.get();

        constituency.setName(dto.getName());
        constituency.setProvince(province);

        return repo.save(constituency);

    }

    public Optional<Constituency> getConstituencyById(Long id) {
        return repo.findById(id);
    }

    public Iterable<Constituency> getAllConstituencies() {
        return repo.findAll();
    }

    public void deleteConstituency(Long id) {
        repo.deleteById(id);
    }
}
