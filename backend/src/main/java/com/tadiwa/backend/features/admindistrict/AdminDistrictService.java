package com.tadiwa.backend.features.admindistrict;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadiwa.backend.exceptions.ProvinceNotFound;
import com.tadiwa.backend.features.admindistrict.dtos.CreateAdminDistrictDTO;
import com.tadiwa.backend.features.provinces.Province;
import com.tadiwa.backend.features.provinces.ProvinceService;

@Service
public class AdminDistrictService {

    @Autowired
    private AdminDistrictsRepository repo;

    @Autowired
    private ProvinceService provService;

    public AdminDistrict createAdminDistrict(CreateAdminDistrictDTO dto) throws ProvinceNotFound {

        Optional<Province> provOptional = provService.getProvinceById(dto.getProvinceId());

        if (provOptional.isEmpty()) {
            throw new ProvinceNotFound();
        }

        Province province = provOptional.get();

        AdminDistrict adminDistrict = new AdminDistrict();

        adminDistrict.setName(dto.getName());
        adminDistrict.setProvince(province);

        return repo.save(adminDistrict);

    }
    
}
