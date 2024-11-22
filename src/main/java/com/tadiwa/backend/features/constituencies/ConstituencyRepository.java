package com.tadiwa.backend.features.constituencies;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstituencyRepository extends CrudRepository<Constituency, Long>  {
    
}
