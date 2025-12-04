package br.com.darioklein.ecopass.domain.mapper;

import br.com.darioklein.ecopass.domain.dto.materialDTO.MaterialDTO;
import br.com.darioklein.ecopass.domain.dto.materialDTO.MaterialPatchDTO;
import br.com.darioklein.ecopass.domain.dto.materialDTO.MaterialResponseDTO;
import br.com.darioklein.ecopass.domain.model.Material;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MaterialMapper {

    Material toEntity(MaterialDTO dto);

    MaterialResponseDTO toResponse(Material entity);

    void updateEntityFromDto(MaterialDTO dto, @MappingTarget Material entity);

    void patchEntityFromDto(MaterialPatchDTO dto, @MappingTarget Material entity);
}
