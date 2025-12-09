package br.com.darioklein.ecopass.domain.dto.recyclingMaterialDTO;

import java.math.BigDecimal;

public record RecyclingMaterialResponseDTO(
        Long recyclingId,
        Long materialId,
        String materialName,
        BigDecimal quantityKg,
        BigDecimal pricePerKg,
        BigDecimal totalPrice
) {
}
