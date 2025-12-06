package br.com.darioklein.ecopass.domain.dto.recyclingMaterialDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record RecyclingMaterialUpdateDTO(

        @Positive(message = "A quantidade deve ser maior que zero")
        @NotNull(message = "A quantidade de kg não pode ser nula")
        BigDecimal quantityKg
) {
}
