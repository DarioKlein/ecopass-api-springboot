package br.com.darioklein.ecopass.domain.dto.materialDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record MaterialDTO(

        @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+$", message = "O nome deve conter apenas letras e espaços")
        @NotBlank(message = "O nome não pode ser nulo")
        String name,

        @Positive(message = "O preço por Kg deve ser maior que zero")
        @NotNull(message = "O preço por Kg não pode ser nulo")
        BigDecimal pricePerKg,

        @NotNull(message = "O campo ativo não pode ser nulo")
        Boolean active
) implements  MaterialUniqueField {

}
