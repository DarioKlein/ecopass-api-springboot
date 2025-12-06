package br.com.darioklein.ecopass.domain.dto.recyclingDTO;

import br.com.darioklein.ecopass.domain.model.enumTypes.Status;

import java.time.LocalDateTime;

public record RecyclingResponseDTO(

        Long id,
        Long userId,
        Status status,
        LocalDateTime registerDate,
        LocalDateTime validationDate,
        String observation
) {
}
