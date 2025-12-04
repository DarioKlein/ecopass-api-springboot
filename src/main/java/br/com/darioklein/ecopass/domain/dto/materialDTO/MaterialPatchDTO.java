package br.com.darioklein.ecopass.domain.dto.materialDTO;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record MaterialPatchDTO(

        @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+$", message = "O nome deve conter apenas letras e espaços")
        String name,

        @Positive(message = "O preço por Kg deve ser maior que zero")
        BigDecimal pricePerKg,

        Boolean active
) implements  MaterialUniqueField {
}
