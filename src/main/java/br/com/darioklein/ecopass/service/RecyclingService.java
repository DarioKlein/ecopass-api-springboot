package br.com.darioklein.ecopass.service;

import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingCreateDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingPatchDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingResponseDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingUpdateDTO;
import br.com.darioklein.ecopass.domain.model.enumTypes.Status;

import java.time.LocalDate;
import java.util.List;

public interface RecyclingService {

    RecyclingResponseDTO findById(Long id);

    List<RecyclingResponseDTO> findAll();

    RecyclingResponseDTO findFirstByRegisterDate(Long userId);

    List<RecyclingResponseDTO> findByUserIdAndStatus(Long userId, Status status);

    List<RecyclingResponseDTO> findByRegisterDateOrValidationDate(LocalDate date);

    List<RecyclingResponseDTO> findByUserIdAndListMaterialName(Long userId, List<String> listMaterialName);

    RecyclingResponseDTO create(RecyclingCreateDTO dto);

    void createAll(List<RecyclingCreateDTO> dtos);

    RecyclingResponseDTO update(Long id, RecyclingUpdateDTO dto);

    void updatePartial(Long id, RecyclingPatchDTO dto);

    void deleteById(Long id);
}
