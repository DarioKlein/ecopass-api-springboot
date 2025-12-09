package br.com.darioklein.ecopass.domain.dto.recyclingMaterialDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record RecyclingMaterialCreateDTO(

        @NotNull(message = "O id da reciclagem não pode ser nulo")
        Long recyclingId,

        @NotNull(message = "O id do material não pode ser nulo")
        Long materialId,

        @Positive(message = "A quantidade deve ser maior que zero")
        @NotNull(message = "A quantidade de kg não pode ser nula")
        BigDecimal quantityKg
) {
}
