package br.com.darioklein.ecopass.service.impl;

import br.com.darioklein.ecopass.domain.dto.recyclingMaterialDTO.RecyclingMaterialCreateDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingMaterialDTO.RecyclingMaterialResponseDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingMaterialDTO.RecyclingMaterialUpdateDTO;
import br.com.darioklein.ecopass.domain.mapper.RecyclingMaterialMapper;
import br.com.darioklein.ecopass.domain.model.entity.Material;
import br.com.darioklein.ecopass.domain.model.entity.Recycling;
import br.com.darioklein.ecopass.domain.model.entity.RecyclingMaterial;
import br.com.darioklein.ecopass.domain.model.entity.id.RecyclingMaterialId;
import br.com.darioklein.ecopass.repository.MaterialRepository;
import br.com.darioklein.ecopass.repository.RecyclingMaterialRepository;
import br.com.darioklein.ecopass.repository.RecyclingRepository;
import br.com.darioklein.ecopass.service.RecyclingMaterialService;
import br.com.darioklein.ecopass.service.exception.DataIntegrityViolationException;
import br.com.darioklein.ecopass.service.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class RecyclingMaterialServiceImpl implements RecyclingMaterialService {

    private final MaterialRepository materialRepository;
    private final RecyclingRepository recyclingRepository;
    private final RecyclingMaterialRepository recyclingMaterialRepository;
    private final RecyclingMaterialMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public RecyclingMaterialResponseDTO findById(RecyclingMaterialId id) {
        RecyclingMaterial recyclingMaterial = recyclingMaterialRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Reciclagens de Materiais não foi encontrada"));
        return mapper.toResponse(recyclingMaterial);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecyclingMaterialResponseDTO> findByRecyclingId(Long id) {
        List<RecyclingMaterial> recyclingMaterialList = recyclingMaterialRepository.findById_RecyclingId(id);
        return recyclingMaterialList.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecyclingMaterialResponseDTO> findAll() {
        List<RecyclingMaterial> recyclingMaterialList = recyclingMaterialRepository.findAll();
        return recyclingMaterialList.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecyclingMaterialResponseDTO> findQuantityKgLessThan(BigDecimal quantityKg) {
        List<RecyclingMaterial> recyclingMaterialList = recyclingMaterialRepository.findByQuantityKgLessThan(quantityKg);
        return recyclingMaterialList.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecyclingMaterialResponseDTO> findQuantityKgGreaterThan(BigDecimal quantityKg) {
        List<RecyclingMaterial> recyclingMaterialList = recyclingMaterialRepository.findByQuantityKgGreaterThan(quantityKg);
        return recyclingMaterialList.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public RecyclingMaterialResponseDTO create(RecyclingMaterialCreateDTO dto) {
        Material material = materialRepository.findById(dto.materialId()).orElseThrow(() -> new ObjectNotFoundException("Material não encontrado"));
        Recycling recycling = recyclingRepository.findById(dto.recyclingId()).orElseThrow(() -> new ObjectNotFoundException("Reciclagem não encontrada"));

        if (recyclingMaterialRepository.existsById(new RecyclingMaterialId(dto.recyclingId(), dto.materialId()))) {
            throw new DataIntegrityViolationException("O id de reciclagem e material passados já foram relacionados anteriormente");
        }

        if(!material.getActive()) {
            throw new DataIntegrityViolationException("Este material está inativo, por favor utilize outro material");
        }

        RecyclingMaterial recyclingMaterial = mapper.toEntity(dto);

        recyclingMaterial.setMaterial(material);
        recyclingMaterial.setRecycling(recycling);

        recyclingMaterialRepository.save(recyclingMaterial);
        return mapper.toResponse(recyclingMaterial);
    }

    @Override
    @Transactional
    public void createAll(List<RecyclingMaterialCreateDTO> dtos) {
        List<RecyclingMaterial> recyclingMaterials = dtos.stream().map((dto) -> {
            Material material = materialRepository.findById(dto.materialId()).orElseThrow(() -> new ObjectNotFoundException("Material não encontrado"));
            Recycling recycling = recyclingRepository.findById(dto.recyclingId()).orElseThrow(() -> new ObjectNotFoundException("Reciclagem não encontrada"));

            RecyclingMaterial recyclingMaterial = mapper.toEntity(dto);
            recyclingMaterial.setMaterial(material);
            recyclingMaterial.setRecycling(recycling);

            return recyclingMaterial;
        }).toList();
        recyclingMaterialRepository.saveAll(recyclingMaterials);
    }

    @Override
    @Transactional
    public RecyclingMaterialResponseDTO update(RecyclingMaterialId id, RecyclingMaterialUpdateDTO dto) {
        RecyclingMaterial recyclingMaterialFound = recyclingMaterialRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Reciclagens de Materiais não foi encontrada"));

        mapper.updateEntityFromDto(dto, recyclingMaterialFound);
        recyclingMaterialRepository.save(recyclingMaterialFound);
        return mapper.toResponse(recyclingMaterialFound);
    }

    @Override
    @Transactional
    public void deleteById(RecyclingMaterialId id) {
        if (!recyclingMaterialRepository.existsById(id)) {
            throw new ObjectNotFoundException("Reciclagens de Materiais não foi encontrada");
        }
        recyclingMaterialRepository.deleteById(id);
    }
}
