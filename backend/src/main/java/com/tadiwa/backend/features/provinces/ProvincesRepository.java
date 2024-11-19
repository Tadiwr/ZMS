package com.tadiwa.backend.features.provinces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvincesRepository extends CrudRepository<Province, Long>  {
    public List<Province> findByName(String name);
}
