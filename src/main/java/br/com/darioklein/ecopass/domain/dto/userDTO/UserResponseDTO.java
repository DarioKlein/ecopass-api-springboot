package br.com.darioklein.ecopass.domain.dto.userDTO;

import br.com.darioklein.ecopass.domain.dto.walletDTO.WalletRespondeDTO;
import br.com.darioklein.ecopass.domain.model.entity.Material;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponseDTO(
        Long id,
        String name,
        String cpf,
        String email,
        String phone,
        LocalDate birthDate,
        LocalDateTime createdAt,
        WalletRespondeDTO wallet,
        List<Material> favoriteMaterials
) {
}
