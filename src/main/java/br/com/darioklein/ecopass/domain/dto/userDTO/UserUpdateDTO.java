package br.com.darioklein.ecopass.domain.dto.userDTO;

import br.com.darioklein.ecopass.utils.MultiLocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserUpdateDTO(

        @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+$", message = "O nome deve conter apenas letras e espaços")
        @NotBlank(message = "O nome não pode ser nulo")
        String name,

        @NotBlank(message = "O CPF não pode ser nulo")
        @Size(min = 11, max = 11, message = "O CPF deve conter 11 dígitos")
        @Pattern(regexp = "^[0-9]+$", message = "O CPF deve conter apenas números")
        String cpf,

        @Email(message = "O e-mail deve ser válido")
        @NotBlank(message = "O E-mail não pode ser nulo")
        String email,

        @NotBlank(message = "O celular não pode ser nulo")
        @Size(min = 11, max = 11, message = "O número de celular deve conter 11 dígitos")
        @Pattern(regexp = "^[0-9]+$", message = "O telefone deve conter apenas números")
        String phone,

        @Past(message = "A data de nascimento não pode ser maior que a data atual")
        @JsonDeserialize(using = MultiLocalDateDeserializer.class)
        LocalDate birthDate
) implements UserUniqueFields {
}
