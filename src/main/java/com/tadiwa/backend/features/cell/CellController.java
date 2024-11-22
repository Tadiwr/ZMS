package com.tadiwa.backend.features.cell;

import org.springframework.beans.factory.annotation.Autowired;
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
    

}
