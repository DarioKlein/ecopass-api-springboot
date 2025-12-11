package br.com.darioklein.ecopass.controller;

import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingCreateDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingPatchDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingResponseDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingUpdateDTO;
import br.com.darioklein.ecopass.domain.model.enumTypes.Status;
import br.com.darioklein.ecopass.service.RecyclingService;
import br.com.darioklein.ecopass.utils.StringUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/recycling")
public class RecyclingController {

    RecyclingService recyclingService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<RecyclingResponseDTO> findRecyclingById(@PathVariable Long id) {
        RecyclingResponseDTO recycling = recyclingService.findById(id);
        return ResponseEntity.ok(recycling);
    }

    @GetMapping
    public ResponseEntity<List<RecyclingResponseDTO>> findAllRecycling() {
        List<RecyclingResponseDTO> recyclingList = recyclingService.findAll();
        return ResponseEntity.ok(recyclingList);
    }

    @GetMapping("/users/{userId}/latest")
    public ResponseEntity<RecyclingResponseDTO> findFirstUserRecyclingByRegisterDate(@PathVariable Long userId) {
        RecyclingResponseDTO recycling = recyclingService.findFirstByRegisterDate(userId);
        return ResponseEntity.ok(recycling);
    }

    @GetMapping("/users/{userId}/{status}")
    public ResponseEntity<List<RecyclingResponseDTO>> findRecyclingByUserIdAndStatus(@PathVariable Long userId, @PathVariable String status) {
        Status formatedStatus = Status.from(status.toUpperCase());
        List<RecyclingResponseDTO> recyclingList = recyclingService.findByUserIdAndStatus(userId, formatedStatus);
        return ResponseEntity.ok(recyclingList);
    }

    @GetMapping("/date-after")
    public ResponseEntity<List<RecyclingResponseDTO>> findRecyclingByDates(@RequestParam LocalDate date) {
        List<RecyclingResponseDTO> recyclingList = recyclingService.findByRegisterDateOrValidationDate(date);
        return ResponseEntity.ok(recyclingList);
    }

    @GetMapping("/users/{userId}/list-material-name")
    public ResponseEntity<List<RecyclingResponseDTO>> findRecyclingByUserIdAndListMaterialName(@PathVariable Long userId, @RequestParam List<String> listMaterialName) {
        List<String> listMaterialNameNormalized = listMaterialName.stream().map(StringUtils::normalize).toList();
        List<RecyclingResponseDTO> recyclingList = recyclingService.findByUserIdAndListMaterialName(userId, listMaterialNameNormalized);
        return ResponseEntity.ok(recyclingList);
    }


    @PostMapping
    public ResponseEntity<RecyclingResponseDTO> createRecycling(@Valid @RequestBody RecyclingCreateDTO dto) {
        RecyclingResponseDTO recycling = recyclingService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(recycling.id()).toUri();
        return ResponseEntity.created(uri).body(recycling);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RecyclingResponseDTO> updateRecycling(@PathVariable Long id, @Valid @RequestBody RecyclingUpdateDTO dto) {
        RecyclingResponseDTO recycling = recyclingService.update(id, dto);
        return ResponseEntity.ok(recycling);
    }


    @PatchMapping(value = "/{id}")
    public ResponseEntity<Void> updatePartialRecycling(@PathVariable Long id, @RequestBody RecyclingPatchDTO dto) {
        recyclingService.updatePartial(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteRecycling(@PathVariable Long id) {
        recyclingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
