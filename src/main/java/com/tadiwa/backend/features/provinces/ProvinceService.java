package com.tadiwa.backend.features.provinces;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadiwa.backend.features.provinces.dto.AddProvinceDTO;
import com.tadiwa.backend.features.provinces.dto.UpdateProvinceDTO;

@Service
public class ProvinceService {
    
    @Autowired
    private ProvincesRepository provincesRepository;

    public Province createProvince(AddProvinceDTO dto) {
        Province province = new Province();
        province.setName(dto.getName());

        return provincesRepository.save(province);
    }

    public Optional<Province> getProvinceById(Long id) {
        return provincesRepository.findById(id);
    }

    public List<Province> getAllProvinces() {
        return provincesRepository.findAll(); 
    }

    public void deleteProvince(Long id) {
        provincesRepository.deleteById(id);
    }

    public Optional<Province> updateProvince(UpdateProvinceDTO dto) {
        Optional<Province> provOptional = provincesRepository.findById(dto.getId());

        if (provOptional.isPresent()) {
            Province province = provOptional.get();
            province.setName(dto.getName());

            return Optional.of(provincesRepository.save(province));
        }

        return Optional.empty();
    }
}
