package com.tadiwa.backend.features.cell;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.tadiwa.backend.features.admindistrict.AdminDistrict;
import com.tadiwa.backend.features.branches.Branch;
import com.tadiwa.backend.features.branches.BranchService;
import com.tadiwa.backend.features.cell.dto.AddCellDTO;
import com.tadiwa.backend.features.cell.dto.UpdateCellDTO;
import com.tadiwa.backend.features.constituencies.Constituency;
import com.tadiwa.backend.features.member.Member;
import com.tadiwa.backend.features.partydistricts.PartyDistrict;
import com.tadiwa.backend.features.pollingstation.PollingStation;
import com.tadiwa.backend.features.pollingstation.PollingStationService;
import com.tadiwa.backend.features.provinces.Province;
import com.tadiwa.backend.features.ward.Ward;
import com.tadiwa.backend.shared.exceptions.NotFound;
import com.tadiwa.backend.shared.utilities.ReportGenerator;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class CellService {
    @Autowired
    private CellRepository cellRepo;

    @Autowired
    private BranchService branchService;

    @Autowired
    private PollingStationService pollingService;

    @Autowired
    ResourceLoader resourceLoader;


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

    public byte[] generateMemberReport(Long cellId) throws NotFound, IOException, JRException {
        Optional<Cell> cellOptional = cellRepo.findById(cellId);

        if (cellOptional.isEmpty()) {
            throw new NotFound();
        }

        Cell cell = cellOptional.get();
        PollingStation pollingStation = cell.getPollingStation();
        Ward ward = pollingStation.getWard();
        Constituency constituency = ward.getConstituency();

        Resource templateFile = resourceLoader.getResource("classpath:static/Member_Report_A4.jrxml");
        ReportGenerator reportGenerator = new ReportGenerator(templateFile);
        Map<String, Object> params = new HashMap<>();

        List<Member> members = cell.getMembers();
        Branch branch = cell.getBranch();
        PartyDistrict partyDistrict = branch.getPartyDistrict();
        AdminDistrict adminDistrict = partyDistrict.getAdminDistrict();
        Province province = adminDistrict.getProvince();

        
        params.put("CellName", cell.getName());
        params.put("ProvinceName", province.getName());
        params.put("AdminDistrictName", adminDistrict.getName());
        params.put("PartyDistrictName", partyDistrict.getName());
        params.put("BranchName", branch.getName());
        params.put("ConstituencyName", constituency.getName());
        params.put("WardName", ward.getName());
        params.put("PollingStationName", pollingStation.getName());
        params.put("MemberCollectionBeanParam", new JRBeanCollectionDataSource(members));
        
        reportGenerator.setItems(members);
        reportGenerator.setParams(params);

        return reportGenerator.generatePdfReport();
        
    }
}
