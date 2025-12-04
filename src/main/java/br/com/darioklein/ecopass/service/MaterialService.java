package br.com.darioklein.ecopass.service;

import br.com.darioklein.ecopass.domain.dto.materialDTO.MaterialDTO;
import br.com.darioklein.ecopass.domain.dto.materialDTO.MaterialPatchDTO;
import br.com.darioklein.ecopass.domain.dto.materialDTO.MaterialResponseDTO;
import br.com.darioklein.ecopass.domain.mapper.MaterialMapper;
import br.com.darioklein.ecopass.domain.model.Material;
import br.com.darioklein.ecopass.repository.MaterialRepository;
import br.com.darioklein.ecopass.service.exception.ObjectNotFoundException;
import br.com.darioklein.ecopass.utils.MaterialValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper mapper;

    @Transactional(readOnly = true)
    public MaterialResponseDTO findById(Long id) {
        Material material = materialRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Material não encontrado"));
        return mapper.toResponse(material);
    }

    @Transactional(readOnly = true)
    public List<MaterialResponseDTO> findAll() {
        List<Material> materials = materialRepository.findAll();
        return materials.stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MaterialResponseDTO> findAllByName(String name) {
        List<Material> material = materialRepository.findAllByNameContainingIgnoreCase(name);
        return material.stream().map(mapper::toResponse).toList();
    }

    @Transactional
    public MaterialResponseDTO create(MaterialDTO dto) {
        MaterialValidator.validateUniqueFieldNameMaterial(materialRepository, dto, null);

        Material materialSaved = materialRepository.save(mapper.toEntity(dto));
        return mapper.toResponse(materialSaved);
    }

    @Transactional
    public void createAll(List<MaterialDTO> dto) {
        List<Material> materials = dto.stream().map(mapper::toEntity).toList();
        materialRepository.saveAll(materials);
    }

    @Transactional
    public MaterialResponseDTO update(Long id, MaterialDTO dto) {
        Material materialFound = materialRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Material não encontrado"));

        MaterialValidator.validateUniqueFieldNameMaterial(materialRepository, dto, materialFound);

        mapper.updateEntityFromDto(dto, materialFound);
        materialFound = materialRepository.save(materialFound);
        return mapper.toResponse(materialFound);
    }

    @Transactional
    public void updatePartial(Long id, MaterialPatchDTO dto) {
        Material materialFound = materialRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Material não encontrado"));

        if (dto.name() != null) {
            MaterialValidator.validateUniqueFieldNameMaterial(materialRepository, dto, materialFound);
        }

        mapper.patchEntityFromDto(dto, materialFound);

        materialRepository.save(materialFound);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!materialRepository.existsById(id)) {
            throw new ObjectNotFoundException("Material não encontrado");
        }
        materialRepository.deleteById(id);
    }
}
