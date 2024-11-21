package com.tadiwa.backend.features.pollingstation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollingStationRepository extends CrudRepository<PollingStation, Long>  {
    
}
