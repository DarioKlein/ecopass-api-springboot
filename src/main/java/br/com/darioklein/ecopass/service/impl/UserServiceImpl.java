package br.com.darioklein.ecopass.service.impl;

import br.com.darioklein.ecopass.domain.dto.userDTO.*;
import br.com.darioklein.ecopass.domain.mapper.UserMapper;
import br.com.darioklein.ecopass.service.UserService;
import br.com.darioklein.ecopass.service.exception.DataIntegrityViolationException;
import br.com.darioklein.ecopass.service.exception.ObjectNotFoundException;
import br.com.darioklein.ecopass.domain.model.entity.User;
import br.com.darioklein.ecopass.repository.UserRepository;
import br.com.darioklein.ecopass.utils.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
        return mapper.toResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAllByDescPendingBalance() {
        List<User> userList = userRepository.findAllByOrderByWallet_PendingBalanceDesc();
        return userList.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> findByBetweenPendingBalance(BigDecimal min, BigDecimal max) {
        List<User> userList = userRepository.findByWallet_PendingBalanceBetween(min, max);
        return userList.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAllByBirthDate() {
        List<User> userList = userRepository.findAllByOrderByBirthDate();
        return userList.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public UserResponseDTO create(UserCreateDTO dto) {
        UserValidator.validateUniqueConstraints(userRepository, dto, null);
        User userCreated = userRepository.save(mapper.toEntity(dto));
        return mapper.toResponse(userCreated);
    }

    @Override
    @Transactional
    public void createAll(List<UserCreateDTO> dtos) {
        List<User> userList = dtos.stream().map(mapper::toEntity).toList();
        userRepository.saveAll(userList);
    }

    @Override
    @Transactional
    public UserResponseDTO update(Long id, UserUpdateDTO dto) {
        User userFound = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));

        UserValidator.validateUniqueConstraints(userRepository, dto, userFound);

        mapper.updateEntityFromDto(dto, userFound);

        userFound = userRepository.save(userFound);
        return mapper.toResponse(userFound);
    }

    @Override
    @Transactional
    public void updatePartial(Long id, UserPatchDTO dto) {
        User userFound = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
        UserValidator.validateUniqueConstraints(userRepository, dto, userFound);

        mapper.patchEntityFromDto(dto, userFound);

        userRepository.save(userFound);
    }

    @Override
    @Transactional
    public void updatePassword(Long id, UserUpdatePasswordDTO newPassword) {
        User userFound = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));

        if (newPassword.password().equals(userFound.getPassword())) {
            throw new DataIntegrityViolationException("A senha informada é idêntica a anterior");
        }

        mapper.passwordEntityFromDto(newPassword, userFound);
        userRepository.save(userFound);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ObjectNotFoundException("Usuário não encontrado");
        }
        userRepository.deleteById(id);
    }
}
