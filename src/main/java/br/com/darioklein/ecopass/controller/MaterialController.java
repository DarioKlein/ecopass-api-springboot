package br.com.darioklein.ecopass.controller;

import br.com.darioklein.ecopass.domain.dto.materialDTO.MaterialDTO;
import br.com.darioklein.ecopass.domain.dto.materialDTO.MaterialPatchDTO;
import br.com.darioklein.ecopass.domain.dto.materialDTO.MaterialResponseDTO;
import br.com.darioklein.ecopass.service.MaterialService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/materials")
public class MaterialController {

    MaterialService materialService;

    @GetMapping("/{id}")
    public ResponseEntity<MaterialResponseDTO> findMaterialById(@PathVariable Long id) {
        MaterialResponseDTO material = materialService.findById(id);
        return ResponseEntity.ok(material);
    }

    @GetMapping
    public ResponseEntity<List<MaterialResponseDTO>> findAllMaterials() {
        List<MaterialResponseDTO> materials = materialService.findAll();
        return ResponseEntity.ok(materials);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<MaterialResponseDTO>> findAllMaterialsByName(@RequestParam String name) {
        List<MaterialResponseDTO> materials = materialService.findAllByName(name);
        return ResponseEntity.ok(materials);
    }

    @PostMapping
    public ResponseEntity<MaterialResponseDTO> createMaterial(@Valid @RequestBody MaterialDTO dto) {
        MaterialResponseDTO material = materialService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(material.id()).toUri();
        return ResponseEntity.created(uri).body(material);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MaterialResponseDTO> updateMaterial(@PathVariable Long id, @Valid @RequestBody MaterialDTO dto) {
        MaterialResponseDTO material = materialService.update(id, dto);
        return ResponseEntity.ok(material);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Void> updatePartialMaterial(@PathVariable Long id, @Valid @RequestBody MaterialPatchDTO dto) {
        materialService.updatePartial(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
        materialService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
