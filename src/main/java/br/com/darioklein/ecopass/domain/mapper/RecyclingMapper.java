package br.com.darioklein.ecopass.domain.mapper;

import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingCreateDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingPatchDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingResponseDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingUpdateDTO;
import br.com.darioklein.ecopass.domain.model.entity.Recycling;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = RecyclingMaterialMapper.class)
public interface RecyclingMapper {

    Recycling toEntity(RecyclingCreateDTO dto);


    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "materialList", target = "materialList")
    RecyclingResponseDTO toResponse(Recycling entity);

    void updateEntityFromDto(RecyclingUpdateDTO dto, @MappingTarget Recycling entity);

    void patchEntityFromDto(RecyclingPatchDTO dto, @MappingTarget Recycling entity);
}
