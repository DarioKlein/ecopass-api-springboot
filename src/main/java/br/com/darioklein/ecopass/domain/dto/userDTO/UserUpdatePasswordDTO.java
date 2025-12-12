package br.com.darioklein.ecopass.domain.dto.userDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdatePasswordDTO(

        @NotBlank(message = "A senha não pode ser nula")
        @Size(min = 8, max = 20, message = "O A senha deve conter no mínimo 8 caracteres e no máximo 20 caracteres")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password
) {
}
