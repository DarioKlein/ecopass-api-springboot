package br.com.darioklein.ecopass.service;

import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingCreateDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingPatchDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingResponseDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingUpdateDTO;
import br.com.darioklein.ecopass.domain.mapper.RecyclingMapper;
import br.com.darioklein.ecopass.domain.model.entity.Recycling;
import br.com.darioklein.ecopass.domain.model.entity.User;
import br.com.darioklein.ecopass.repository.RecyclingRepository;
import br.com.darioklein.ecopass.repository.UserRepository;
import br.com.darioklein.ecopass.service.exception.ObjectNotFoundException;
import br.com.darioklein.ecopass.utils.RecyclingValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class RecyclingService {

    private final RecyclingRepository recyclingRepository;
    private final UserRepository userRepository;
    private final RecyclingMapper mapper;

    @Transactional(readOnly = true)
    public RecyclingResponseDTO findById(Long id) {
        Recycling recycling = recyclingRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Reciclagem não encontrada"));
        return mapper.toResponse(recycling);
    }

    @Transactional(readOnly = true)
    public List<RecyclingResponseDTO> findAll() {
        List<Recycling> recyclingList = recyclingRepository.findAll();
        return recyclingList.stream().map(mapper::toResponse).toList();
    }

    @Transactional
    public RecyclingResponseDTO create(RecyclingCreateDTO dto) {
        User user = userRepository.findById(dto.userId()).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));

        Recycling recycling = mapper.toEntity(dto);
        recycling.setUser(user);

        Recycling recyclingSaved = recyclingRepository.save(recycling);
        return mapper.toResponse(recyclingSaved);
    }

    @Transactional
    public void createAll(List<RecyclingCreateDTO> dtos) {
        List<Recycling> recyclingList = dtos.stream().map((dto) -> {
            User user = userRepository.findById(dto.userId()).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));

            Recycling recycling = mapper.toEntity(dto);
            recycling.setUser(user);

            return recycling;
        }).toList();

        recyclingRepository.saveAll(recyclingList);
    }

    @Transactional
    public RecyclingResponseDTO update(Long id, RecyclingUpdateDTO dto) {
        Recycling recyclingFound = recyclingRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Reciclagem não encontrada"));

        mapper.updateEntityFromDto(dto, recyclingFound);

        recyclingFound.setValidationDate(RecyclingValidator.recyclingStatusValidator(dto.status()));

        recyclingFound = recyclingRepository.save(recyclingFound);
        return mapper.toResponse(recyclingFound);
    }

    @Transactional
    public void updatePartial(Long id, RecyclingPatchDTO dto) {
        Recycling recyclingFound = recyclingRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Reciclagem não encontrada"));

        mapper.patchEntityFromDto(dto, recyclingFound);

        if(dto.status() != null) {
            recyclingFound.setValidationDate(RecyclingValidator.recyclingStatusValidator(dto.status()));
        }

        recyclingRepository.save(recyclingFound);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!recyclingRepository.existsById(id)) {
            throw new ObjectNotFoundException("Reciclagem não encontrada");
        }
        recyclingRepository.deleteById(id);
    }

}
