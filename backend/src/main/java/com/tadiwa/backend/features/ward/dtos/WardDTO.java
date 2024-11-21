package com.tadiwa.backend.features.ward.dtos;

import java.util.List;

import com.tadiwa.backend.features.pollingstation.PollingStation;

public record WardDTO (
    Long id,
    String name,
    Long constituencyId,
    List<PollingStation> pollingStations
) {
    
}
