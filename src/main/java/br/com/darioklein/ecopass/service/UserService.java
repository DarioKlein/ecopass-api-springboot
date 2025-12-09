package br.com.darioklein.ecopass.service;

import br.com.darioklein.ecopass.domain.dto.userDTO.UserCreateDTO;
import br.com.darioklein.ecopass.domain.dto.userDTO.UserResponseDTO;
import br.com.darioklein.ecopass.domain.dto.userDTO.UserUpdateDTO;
import br.com.darioklein.ecopass.domain.dto.userDTO.UserPatchDTO;
import br.com.darioklein.ecopass.domain.mapper.UserMapper;
import br.com.darioklein.ecopass.service.exception.ObjectNotFoundException;
import br.com.darioklein.ecopass.domain.model.entity.User;
import br.com.darioklein.ecopass.repository.UserRepository;
import br.com.darioklein.ecopass.utils.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Transactional(readOnly = true)
    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
        return mapper.toResponse(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(mapper::toResponse).toList();
    }

    @Transactional
    public UserResponseDTO create(UserCreateDTO dto) {
        UserValidator.validateUniqueConstraints(userRepository, dto, null);
        User userCreated = userRepository.save(mapper.toEntity(dto));
        return mapper.toResponse(userCreated);
    }

    @Transactional
    public void createAll(List<UserCreateDTO> dtos) {
        List<User> userList = dtos.stream().map(mapper::toEntity).toList();
        userRepository.saveAll(userList);
    }

    @Transactional
    public UserResponseDTO update(Long id, UserUpdateDTO dto) {
        User userFound = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));

        UserValidator.validateUniqueConstraints(userRepository, dto, userFound);

        mapper.updateEntityFromDto(dto, userFound);

        userFound = userRepository.save(userFound);
        return mapper.toResponse(userFound);
    }

    @Transactional
    public void updatePartial(Long id, UserPatchDTO dto) {
        User userFound = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
        UserValidator.validateUniqueConstraints(userRepository, dto, userFound);

        mapper.patchEntityFromDto(dto, userFound);

        userRepository.save(userFound);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ObjectNotFoundException("Usuário não encontrado");
        }
        userRepository.deleteById(id);
    }
}
