package com.tadiwa.backend.features.cell;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tadiwa.backend.features.cell.dto.AddCellDTO;
import com.tadiwa.backend.features.cell.dto.CellDTO;
import com.tadiwa.backend.features.cell.dto.UpdateCellDTO;
import com.tadiwa.backend.shared.exceptions.NotFound;
import com.tadiwa.backend.shared.utilities.RestControllerUtils;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("cells")
@OpenAPIDefinition(
    info = @Info(
        description = "Api for Cells"
    )
)
public class CellController extends RestControllerUtils {
    
    @Autowired
    private CellService cellService;

    
    @GetMapping("")
    public Iterable<CellDTO> all() {
        return transform(cellService.getAllCells());
    }


    @GetMapping("/{id}")
    public ResponseEntity<CellDTO> getCellById(@PathVariable Long id) {
        return might404(cellService.findCellById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<CellDTO> addCell(@RequestBody AddCellDTO dto) {
        try {
            Cell cell = cellService.createCell(dto);

            return ResponseEntity.ok(transform(cell));
        } catch(NotFound e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<CellDTO> updateCell(@RequestBody UpdateCellDTO dto) {
        try {
            Cell cell = cellService.updateCell(dto);

            return ResponseEntity.ok(transform(cell));
        } catch(NotFound e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCell(@PathVariable Long id) {
        cellService.deleteCell(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/report/{cellId}")
    public ResponseEntity<byte[]> cellReport(@PathVariable Long cellId) {
        try {
            byte[] pdfBytes = cellService.generateMemberReport(cellId);

            HttpHeaders headers = new HttpHeaders(); 

            headers.setContentType(MediaType.APPLICATION_PDF);
            // headers.setContentDisposition(
            //     ContentDisposition.builder("attachment")
            //     .filename("cell_report.pdf")
            //     .build()
            // );

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.notFound().build();
        }
    }
    
    

}
