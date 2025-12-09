package br.com.darioklein.ecopass.controller;

import br.com.darioklein.ecopass.domain.dto.recyclingMaterialDTO.RecyclingMaterialCreateDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingMaterialDTO.RecyclingMaterialResponseDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingMaterialDTO.RecyclingMaterialUpdateDTO;
import br.com.darioklein.ecopass.domain.model.entity.id.RecyclingMaterialId;
import br.com.darioklein.ecopass.service.RecyclingMaterialService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/recycling-materials")
public class RecyclingMaterialController {

    private final RecyclingMaterialService recyclingMaterialService;

    @GetMapping("/search-by-id")
    public ResponseEntity<RecyclingMaterialResponseDTO> findRecyclingMaterialById(@RequestParam Long materialId, @RequestParam Long recyclingId) {
        RecyclingMaterialId id = new RecyclingMaterialId(materialId, recyclingId);
        return ResponseEntity.ok(recyclingMaterialService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RecyclingMaterialResponseDTO>> findAllRecyclingMaterial() {
        List<RecyclingMaterialResponseDTO> list = recyclingMaterialService.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<RecyclingMaterialResponseDTO> createRecyclingMaterial(@Valid @RequestBody RecyclingMaterialCreateDTO dto) {
        RecyclingMaterialResponseDTO recyclingMaterial = recyclingMaterialService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/recycling-materials/search-by-id").build().toUri();
        return ResponseEntity.created(uri).body(recyclingMaterial);
    }

    @PutMapping
    public ResponseEntity<RecyclingMaterialResponseDTO> updateRecyclingMaterial(@RequestParam Long materialId, @RequestParam Long recyclingId, @Valid @RequestBody RecyclingMaterialUpdateDTO dto) {
        RecyclingMaterialId id = new RecyclingMaterialId(materialId, recyclingId);
        RecyclingMaterialResponseDTO recyclingMaterial = recyclingMaterialService.update(id, dto);
        return ResponseEntity.ok(recyclingMaterial);
    }

    @DeleteMapping
    public void deleteRecyclingMaterial(@RequestParam Long materialId, @RequestParam Long recyclingId) {
        RecyclingMaterialId id = new RecyclingMaterialId(materialId, recyclingId);
        recyclingMaterialService.deleteById(id);
    }
}
