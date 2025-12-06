package br.com.darioklein.ecopass.domain.mapper;

import br.com.darioklein.ecopass.domain.dto.walletDTO.WalletCreateDTO;
import br.com.darioklein.ecopass.domain.dto.walletDTO.WalletPatchDTO;
import br.com.darioklein.ecopass.domain.dto.walletDTO.WalletRespondeDTO;
import br.com.darioklein.ecopass.domain.dto.walletDTO.WalletUpdateDTO;
import br.com.darioklein.ecopass.domain.model.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface WalletMapper {

    Wallet toEntity(WalletCreateDTO dto);

    @Mapping(source = "user.id", target = "userId")
    WalletRespondeDTO toResponse(Wallet entity);

    void updateEntityFromDto(WalletUpdateDTO dto, @MappingTarget Wallet entity);

    void patchEntityFromDto(WalletPatchDTO dto, @MappingTarget Wallet entity);

}
