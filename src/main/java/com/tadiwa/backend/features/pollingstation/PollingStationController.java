package com.tadiwa.backend.features.pollingstation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tadiwa.backend.features.pollingstation.dtos.AddPollingStationDTO;
import com.tadiwa.backend.features.pollingstation.dtos.PollingStationDTO;
import com.tadiwa.backend.features.pollingstation.dtos.UpdatePollingStationDTO;
import com.tadiwa.backend.shared.utilities.RestControllerUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("polling-stations")
public class PollingStationController extends RestControllerUtils {
    
    @Autowired
    private PollingStationService pollingStationService;

    @GetMapping("")
    public Iterable<PollingStationDTO> getAll() {
        Iterable<PollingStation> stations = pollingStationService.getAllPollingStations();
        return transform(stations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PollingStationDTO> getPollingStationById(@PathVariable Long id) {
        return might404(
            pollingStationService.getPollingStationById(id)
        );
    }

    @PostMapping("/add")
    public ResponseEntity<PollingStationDTO> addPollingStation(@RequestBody AddPollingStationDTO dto) {
        try {
            PollingStation pollingStation = pollingStationService.createPollingStation(dto);

            return ResponseEntity.ok(transform(pollingStation));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<PollingStationDTO> updatePollingStation(@RequestBody UpdatePollingStationDTO dto) {
        try {
            PollingStation pollingStation = pollingStationService.updatePollingStation(dto);

            return ResponseEntity.ok(transform(pollingStation));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePollingStation(@PathVariable Long id) {
        pollingStationService.deletePollingStation(id);

        return ResponseEntity.ok().build();
    }
    

}
