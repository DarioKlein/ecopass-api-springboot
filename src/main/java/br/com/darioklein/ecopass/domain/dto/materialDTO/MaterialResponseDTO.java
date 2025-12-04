package br.com.darioklein.ecopass.domain.dto.materialDTO;

import java.math.BigDecimal;

public record MaterialResponseDTO(

        Long id,
        String name,
        BigDecimal pricePerKg,
        Boolean active
) {
}
