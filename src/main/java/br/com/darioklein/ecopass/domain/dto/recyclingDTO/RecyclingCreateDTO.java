package br.com.darioklein.ecopass.domain.dto.recyclingDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RecyclingCreateDTO(

        @NotNull(message = "O userId não pode ser nulo")
        Long userId,

        @Pattern(regexp = "^[a-zA-Z0-9 .,!?]*$", message = "Observação contém caracteres inválidos")
        @Size(max = 500, message = "A observação não pode ter mais de 500 caracteres")
        String observation
) {
}
