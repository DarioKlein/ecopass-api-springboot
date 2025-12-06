package br.com.darioklein.ecopass.controller;

import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingCreateDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingPatchDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingResponseDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingUpdateDTO;
import br.com.darioklein.ecopass.service.RecyclingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/recycling")
public class RecyclingController {

    RecyclingService recyclingService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<RecyclingResponseDTO> findRecyclingById(@PathVariable Long id){
        RecyclingResponseDTO recycling = recyclingService.findById(id);
        return  ResponseEntity.ok(recycling);
    }

    @GetMapping
    public ResponseEntity<List<RecyclingResponseDTO>> findAllRecycling(){
        List<RecyclingResponseDTO> recyclingList = recyclingService.findAll();
        return ResponseEntity.ok(recyclingList);
    }

    @PostMapping
    public ResponseEntity<RecyclingResponseDTO> createRecycling(@Valid @RequestBody RecyclingCreateDTO dto) {
        RecyclingResponseDTO recycling = recyclingService.create(dto);
        URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(recycling.id()).toUri();
        return  ResponseEntity.created(uri).body(recycling);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RecyclingResponseDTO> updateRecycling(@PathVariable Long id, @Valid @RequestBody RecyclingUpdateDTO dto) {
        RecyclingResponseDTO recycling = recyclingService.update(id, dto);
        return  ResponseEntity.ok(recycling);
    }


    @PatchMapping(value = "/{id}")
    public ResponseEntity<Void> updatePartialRecycling(@PathVariable Long id, @RequestBody RecyclingPatchDTO dto) {
        recyclingService.updatePartial(id, dto);
        return  ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteRecycling(@PathVariable Long id){
        recyclingService.deleteById(id);
        return  ResponseEntity.noContent().build();
    }
}
