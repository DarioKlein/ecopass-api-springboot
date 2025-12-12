package br.com.darioklein.ecopass.service;

import br.com.darioklein.ecopass.domain.dto.userDTO.*;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {

    UserResponseDTO findById(Long id);

    List<UserResponseDTO> findAll();

    List<UserResponseDTO> findAllByDescPendingBalance();

    List<UserResponseDTO> findByBetweenPendingBalance(BigDecimal min, BigDecimal max);

    List<UserResponseDTO> findAllByBirthDate();

    UserResponseDTO create(UserCreateDTO dto);

    void createAll(List<UserCreateDTO> dtos);

    UserResponseDTO update(Long id, UserUpdateDTO dto);

    void updatePartial(Long id, UserPatchDTO dto);

    void updatePassword(Long id, UserUpdatePasswordDTO newPassword);

    void deleteById(Long id);
}
