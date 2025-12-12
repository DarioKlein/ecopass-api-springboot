package br.com.darioklein.ecopass.service;

import br.com.darioklein.ecopass.domain.dto.materialDTO.MaterialDTO;
import br.com.darioklein.ecopass.domain.dto.materialDTO.MaterialPatchDTO;
import br.com.darioklein.ecopass.domain.dto.materialDTO.MaterialResponseDTO;

import java.math.BigDecimal;
import java.util.List;

public interface MaterialService {

    MaterialResponseDTO findById(Long id);

    List<MaterialResponseDTO> findAll();

    List<MaterialResponseDTO> findAllByName(String name);

    List<MaterialResponseDTO> findByLessThanEqualPricePerKg(BigDecimal pricePerKg);

    List<MaterialResponseDTO> findByGreaterThanEqualPricePerKg(BigDecimal pricePerKg);

    MaterialResponseDTO create(MaterialDTO dto);

    void createAll(List<MaterialDTO> dto);

    MaterialResponseDTO update(Long id, MaterialDTO dto);

    void updatePartial(Long id, MaterialPatchDTO dto);

    void deleteById(Long id);
}
