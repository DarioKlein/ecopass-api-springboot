package br.com.darioklein.ecopass.domain.dto.recyclingMaterialDTO;

import java.math.BigDecimal;

public record RecylingMaterialResponseDTO(
        Long recyclingId,
        Long materialId,
        String materialName,
        BigDecimal quantidadeKg,
        BigDecimal pricePerKg,
        BigDecimal totalPrice
) {
}
