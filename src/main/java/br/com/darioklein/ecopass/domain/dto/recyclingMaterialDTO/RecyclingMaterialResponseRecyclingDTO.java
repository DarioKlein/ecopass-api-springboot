package br.com.darioklein.ecopass.domain.dto.recyclingMaterialDTO;

import java.math.BigDecimal;

public record RecyclingMaterialResponseRecyclingDTO(
        String materialName,
        BigDecimal quantityKg,
        BigDecimal pricePerKg,
        BigDecimal totalPrice
) {
}
