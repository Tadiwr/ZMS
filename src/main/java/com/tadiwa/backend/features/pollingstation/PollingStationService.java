package com.tadiwa.backend.features.pollingstation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadiwa.backend.features.pollingstation.dtos.AddPollingStationDTO;
import com.tadiwa.backend.features.pollingstation.dtos.UpdatePollingStationDTO;
import com.tadiwa.backend.features.ward.Ward;
import com.tadiwa.backend.features.ward.WardService;
import com.tadiwa.backend.shared.exceptions.NotFound;

@Service
public class PollingStationService {

    @Autowired
    private PollingStationRepository pollingStationRepo;

    @Autowired
    private WardService wardService;

    public Iterable<PollingStation> getAllPollingStations() {
        return pollingStationRepo.findAll();
    }

    public Optional<PollingStation> getPollingStationById(Long id) {
        return pollingStationRepo.findById(id);
    }

    public PollingStation createPollingStation(AddPollingStationDTO dto) throws NotFound {
        Optional<Ward> wardOptional = wardService.getWardById(dto.getWardId());

        if (wardOptional.isEmpty()) {
            throw new NotFound();
        }

        Ward ward = wardOptional.get();
        PollingStation pollingStation = new PollingStation();

        pollingStation.setName(dto.getName());
        pollingStation.setWard(ward);

        return pollingStationRepo.save(pollingStation);
    }

    public PollingStation updatePollingStation(UpdatePollingStationDTO dto) throws NotFound {
        Optional<Ward> wardOptional = wardService.getWardById(dto.getWardId());
        Optional<PollingStation> pollingOptional = pollingStationRepo.findById(dto.getId());

        if (wardOptional.isEmpty() || pollingOptional.isEmpty()) {
            throw new NotFound();
        }

        Ward ward = wardOptional.get();
        PollingStation pollingStation = pollingOptional.get();

        pollingStation.setName(dto.getName());
        pollingStation.setWard(ward);

        return pollingStationRepo.save(pollingStation);
    }

    public void deletePollingStation(Long id) {
        pollingStationRepo.deleteById(id);
    }
    
}
