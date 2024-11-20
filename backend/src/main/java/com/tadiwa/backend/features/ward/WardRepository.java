package com.tadiwa.backend.features.ward;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends CrudRepository<Ward, Long> {
    
}
