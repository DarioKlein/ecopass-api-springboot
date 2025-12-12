package br.com.darioklein.ecopass.controller;

import br.com.darioklein.ecopass.domain.dto.recyclingMaterialDTO.RecyclingMaterialCreateDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingMaterialDTO.RecyclingMaterialResponseDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingMaterialDTO.RecyclingMaterialUpdateDTO;
import br.com.darioklein.ecopass.domain.model.entity.id.RecyclingMaterialId;
import br.com.darioklein.ecopass.service.impl.RecyclingMaterialServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/recycling-materials")
public class RecyclingMaterialController {

    private final RecyclingMaterialServiceImpl recyclingMaterialService;

    @GetMapping("/search-by-id")
    public ResponseEntity<RecyclingMaterialResponseDTO> findRecyclingMaterialById(@RequestParam Long recyclingId, @RequestParam Long materialId) {
        RecyclingMaterialId id = new RecyclingMaterialId(recyclingId, materialId);
        return ResponseEntity.ok(recyclingMaterialService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RecyclingMaterialResponseDTO>> findAllRecyclingMaterial() {
        List<RecyclingMaterialResponseDTO> RecyclingMaterialList = recyclingMaterialService.findAll();
        return ResponseEntity.ok(RecyclingMaterialList);
    }

    @GetMapping("/search-by-recycling/{id}")
    public ResponseEntity<List<RecyclingMaterialResponseDTO>> findRecyclingMaterialByRecyclingId(@PathVariable Long id) {
        List<RecyclingMaterialResponseDTO> recyclingMaterialList = recyclingMaterialService.findByRecyclingId(id);
        return ResponseEntity.ok(recyclingMaterialList);
    }

    @GetMapping("/less-than")
    public ResponseEntity<List<RecyclingMaterialResponseDTO>> findRecyclingMaterialByQuantityKgLessThan(@RequestParam BigDecimal quantityKg) {
        List<RecyclingMaterialResponseDTO> recyclingMaterialList = recyclingMaterialService.findQuantityKgLessThan(quantityKg);
        return ResponseEntity.ok(recyclingMaterialList);
    }

    @GetMapping("/greater-than")
    public ResponseEntity<List<RecyclingMaterialResponseDTO>> findRecyclingMaterialByQuantityKgGreaterThan(@RequestParam BigDecimal quantityKg) {
        List<RecyclingMaterialResponseDTO> recyclingMaterialList = recyclingMaterialService.findQuantityKgGreaterThan(quantityKg);
        return ResponseEntity.ok(recyclingMaterialList);
    }

    @PostMapping
    public ResponseEntity<RecyclingMaterialResponseDTO> createRecyclingMaterial(@Valid @RequestBody RecyclingMaterialCreateDTO dto) {
        RecyclingMaterialResponseDTO recyclingMaterial = recyclingMaterialService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/recycling-materials/search-by-id").build().toUri();
        return ResponseEntity.created(uri).body(recyclingMaterial);
    }

    @PutMapping
    public ResponseEntity<RecyclingMaterialResponseDTO> updateRecyclingMaterial(@RequestParam Long recyclingId, @RequestParam Long materialId, @Valid @RequestBody RecyclingMaterialUpdateDTO dto) {
        RecyclingMaterialId id = new RecyclingMaterialId(recyclingId, materialId);
        RecyclingMaterialResponseDTO recyclingMaterial = recyclingMaterialService.update(id, dto);
        return ResponseEntity.ok(recyclingMaterial);
    }

    @DeleteMapping
    public void deleteRecyclingMaterial(@RequestParam Long recyclingId, @RequestParam Long materialId) {
        RecyclingMaterialId id = new RecyclingMaterialId(recyclingId, materialId);
        recyclingMaterialService.deleteById(id);
    }
}
