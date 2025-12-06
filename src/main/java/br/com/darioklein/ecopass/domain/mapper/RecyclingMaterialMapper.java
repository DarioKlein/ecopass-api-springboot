package br.com.darioklein.ecopass.domain.mapper;

import br.com.darioklein.ecopass.domain.dto.recyclingMaterialDTO.RecyclingMaterialDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingMaterialDTO.RecyclingMaterialUpdateDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingMaterialDTO.RecylingMaterialResponseDTO;
import br.com.darioklein.ecopass.domain.model.entity.RecyclingMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RecyclingMaterialMapper {

    RecyclingMaterial toEntity(RecyclingMaterialDTO dto);

    @Mapping(source = "recycling.id", target = "recyclingId")
    @Mapping(source = "material.id", target = "materialId")
    @Mapping(source = "material.name", target = "materialName")
    @Mapping(source = "material.pricePerKg", target = "pricePerKg")
    @Mapping(target = "totalPrice", expression = "java(entity.getQuantityKg().multiply(entity.getMaterial().getPricePerKg()))")
    RecylingMaterialResponseDTO toResponse(RecyclingMaterial entity);

    void updateEntityFromDto(RecyclingMaterialUpdateDTO dto,  @MappingTarget RecyclingMaterial entity);
}
