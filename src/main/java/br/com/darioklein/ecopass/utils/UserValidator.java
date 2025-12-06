package br.com.darioklein.ecopass.utils;

import br.com.darioklein.ecopass.domain.dto.userDTO.UserUniqueFields;
import br.com.darioklein.ecopass.domain.model.entity.User;
import br.com.darioklein.ecopass.repository.UserRepository;
import br.com.darioklein.ecopass.service.exception.DataIntegrityViolationException;

public class UserValidator {

    public static void validateUniqueConstraints(UserRepository userRepository, UserUniqueFields dto, User userFound) {
        if (userRepository.existsByCpf(dto.cpf()) && (userFound == null || !userFound.getCpf().equals(dto.cpf()))) {
            throw new DataIntegrityViolationException("O CPF informado já foi cadastrado");
        }

        if (userRepository.existsByEmail(dto.email()) && (userFound == null || !userFound.getEmail().equals(dto.email()))) {
            throw new DataIntegrityViolationException("O E-mail informado já foi cadastrado");
        }

        if (userRepository.existsByPhone(dto.phone()) && (userFound == null || !userFound.getPhone().equals(dto.phone()))) {
            throw new DataIntegrityViolationException("O telefone informado já foi cadastrado");
        }
    }
}
