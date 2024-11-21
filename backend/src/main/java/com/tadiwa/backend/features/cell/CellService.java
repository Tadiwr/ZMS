package com.tadiwa.backend.features.cell;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadiwa.backend.features.branches.Branch;
import com.tadiwa.backend.features.branches.BranchService;
import com.tadiwa.backend.features.cell.dto.AddCellDTO;
import com.tadiwa.backend.features.cell.dto.UpdateCellDTO;
import com.tadiwa.backend.features.pollingstation.PollingStation;
import com.tadiwa.backend.features.pollingstation.PollingStationService;
import com.tadiwa.backend.shared.exceptions.NotFound;

@Service
public class CellService {
    @Autowired
    private CellRepository cellRepo;

    @Autowired
    private BranchService branchService;

    @Autowired
    private PollingStationService pollingService;


    public Iterable<Cell> getAllCells() {
        return cellRepo.findAll();
    }

    public Optional<Cell> findCellById(Long id) {
        return cellRepo.findById(id);
    }

    public Cell createCell(AddCellDTO dto) throws NotFound {
        Optional<Branch> branchOptional = branchService.getBranchById(dto.getBranchId());
        Optional<PollingStation> pollingOptional = pollingService.getPollingStationById(dto.getPollingStationId());

        if (branchOptional.isEmpty() || pollingOptional.isEmpty()) {
            throw new NotFound();
        }

        Branch branch = branchOptional.get();
        PollingStation pollingStation = pollingOptional.get();

        Cell cell = new Cell();

        cell.setName(dto.getName());
        cell.setBranch(branch);
        cell.setPollingStation(pollingStation);

        return cellRepo.save(cell);
    }

    public Cell updateCell(UpdateCellDTO dto) throws NotFound {
        Optional<Branch> branchOptional = branchService.getBranchById(dto.getBranchId());
        Optional<PollingStation> pollingOptional = pollingService.getPollingStationById(dto.getPollingStationId());
        Optional<Cell> cellOptional = cellRepo.findById(dto.getId());

        if (branchOptional.isEmpty() || pollingOptional.isEmpty() || cellOptional.isEmpty()) {
            throw new NotFound();
        }

        Branch branch = branchOptional.get();
        PollingStation pollingStation = pollingOptional.get();

        Cell cell = cellOptional.get();

        cell.setName(dto.getName());
        cell.setBranch(branch);
        cell.setPollingStation(pollingStation);

        return cellRepo.save(cell);
    }

    public void deleteCell(Long id) {
        cellRepo.deleteById(id);
    }
}
