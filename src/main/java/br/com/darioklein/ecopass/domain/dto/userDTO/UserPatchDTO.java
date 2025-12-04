package br.com.darioklein.ecopass.domain.dto.userDTO;

import br.com.darioklein.ecopass.utils.MultiLocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserPatchDTO(

        @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+$", message = "O nome deve conter apenas letras e espaços")
        String name,

        @Size(min = 11, max = 11, message = "O CPF deve conter 11 dígitos")
        @Pattern(regexp = "^[0-9]+$", message = "O CPF deve conter apenas números")
        String cpf,

        @Email(message = "O e-mail deve ser válido")
        String email,

        @Size(min = 11, max = 11, message = "O número de celular deve conter 11 dígitos")
        @Pattern(regexp = "^[0-9]+$", message = "O telefone deve conter apenas números")
        String phone,

        @Past(message = "A data de nascimento não pode ser maior que a data atual")
        @JsonDeserialize(using = MultiLocalDateDeserializer.class)
        LocalDate birthDate
) implements UserUniqueFields {
}
