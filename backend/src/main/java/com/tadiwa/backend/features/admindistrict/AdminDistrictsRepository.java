package com.tadiwa.backend.features.admindistrict;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDistrictsRepository extends CrudRepository<AdminDistrict, Long> {
    
}
