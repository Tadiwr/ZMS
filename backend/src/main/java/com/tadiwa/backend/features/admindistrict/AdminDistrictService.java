package com.tadiwa.backend.features.admindistrict;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadiwa.backend.exceptions.NotFound;
import com.tadiwa.backend.exceptions.ProvinceNotFound;
import com.tadiwa.backend.features.admindistrict.dtos.CreateAdminDistrictDTO;
import com.tadiwa.backend.features.admindistrict.dtos.UpdateAdminDistrictDTO;
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

    public void deleteAdminDistrict(Long id) {
        repo.deleteById(id);
    }

    public Iterable<AdminDistrict> getAllAdminDistricts() {
        return repo.findAll();
    }

    public AdminDistrict updateAdminDistrict(UpdateAdminDistrictDTO dto) throws NotFound {
        Optional<AdminDistrict> adminDistOptional = repo.findById(dto.getId());

        if (adminDistOptional.isEmpty()) {
            throw new NotFound();
        }

        Optional<Province> provOptional = provService.getProvinceById(dto.getProvinceId());

        if (provOptional.isEmpty()) {
            throw new NotFound();
        }


        Province province = provOptional.get();
        AdminDistrict adminDistrict = adminDistOptional.get();

        adminDistrict.setName(dto.getName());
        adminDistrict.setProvince(province);

        return repo.save(adminDistrict);
    }
    
}
