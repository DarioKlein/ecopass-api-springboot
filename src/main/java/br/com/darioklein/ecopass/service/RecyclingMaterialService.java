package br.com.darioklein.ecopass.service;

import br.com.darioklein.ecopass.domain.dto.recyclingMaterialDTO.RecyclingMaterialCreateDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingMaterialDTO.RecyclingMaterialResponseDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingMaterialDTO.RecyclingMaterialUpdateDTO;
import br.com.darioklein.ecopass.domain.model.entity.id.RecyclingMaterialId;

import java.math.BigDecimal;
import java.util.List;

public interface RecyclingMaterialService {

    RecyclingMaterialResponseDTO findById(RecyclingMaterialId id);

    List<RecyclingMaterialResponseDTO> findByRecyclingId(Long id);

    List<RecyclingMaterialResponseDTO> findAll();

    List<RecyclingMaterialResponseDTO> findQuantityKgLessThan(BigDecimal quantityKg);

    List<RecyclingMaterialResponseDTO> findQuantityKgGreaterThan(BigDecimal quantityKg);

    RecyclingMaterialResponseDTO create(RecyclingMaterialCreateDTO dto);

    void createAll(List<RecyclingMaterialCreateDTO> dtos);

    RecyclingMaterialResponseDTO update(RecyclingMaterialId id, RecyclingMaterialUpdateDTO dto);

    void deleteById(RecyclingMaterialId id);
}
