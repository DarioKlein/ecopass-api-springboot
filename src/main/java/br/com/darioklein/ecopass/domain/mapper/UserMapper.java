package br.com.darioklein.ecopass.domain.mapper;

import br.com.darioklein.ecopass.domain.dto.userDTO.*;
import br.com.darioklein.ecopass.domain.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = WalletMapper.class)
public interface UserMapper {

    User toEntity(UserCreateDTO dto);

    UserResponseDTO toResponse(User entity);

    void updateEntityFromDto(UserUpdateDTO dto, @MappingTarget User entity);

    void patchEntityFromDto(UserPatchDTO dto, @MappingTarget User entity);

    void passwordEntityFromDto(UserUpdatePasswordDTO dto, @MappingTarget User entity);
}
