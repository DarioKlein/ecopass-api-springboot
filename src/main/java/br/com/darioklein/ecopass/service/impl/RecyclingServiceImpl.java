package br.com.darioklein.ecopass.service.impl;

import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingCreateDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingPatchDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingResponseDTO;
import br.com.darioklein.ecopass.domain.dto.recyclingDTO.RecyclingUpdateDTO;
import br.com.darioklein.ecopass.domain.mapper.RecyclingMapper;
import br.com.darioklein.ecopass.domain.model.entity.Recycling;
import br.com.darioklein.ecopass.domain.model.entity.User;
import br.com.darioklein.ecopass.domain.model.enumTypes.Status;
import br.com.darioklein.ecopass.repository.RecyclingRepository;
import br.com.darioklein.ecopass.repository.UserRepository;
import br.com.darioklein.ecopass.service.RecyclingService;
import br.com.darioklein.ecopass.service.exception.ObjectNotFoundException;
import br.com.darioklein.ecopass.utils.RecyclingValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class RecyclingServiceImpl implements RecyclingService {

    private final RecyclingRepository recyclingRepository;
    private final UserRepository userRepository;
    private final RecyclingMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public RecyclingResponseDTO findById(Long id) {
        Recycling recycling = recyclingRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Reciclagem não encontrada"));
        return mapper.toResponse(recycling);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecyclingResponseDTO> findAll() {
        List<Recycling> recyclingList = recyclingRepository.findAll();
        return recyclingList.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RecyclingResponseDTO findFirstByRegisterDate(Long userId) {
        Recycling recycling = recyclingRepository.findFirstByUserIdOrderByRegisterDateDesc(userId);
        return mapper.toResponse(recycling);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecyclingResponseDTO> findByUserIdAndStatus(Long userId, Status status) {
        List<Recycling> recyclingList = recyclingRepository.findByUserIdAndStatus(userId, status);
        return recyclingList.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecyclingResponseDTO> findByRegisterDateOrValidationDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        List<Recycling> recyclingList = recyclingRepository.findByRegisterDateAfterOrValidationDateAfter(startOfDay, startOfDay);
        return recyclingList.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecyclingResponseDTO> findByUserIdAndListMaterialName(Long userId, List<String> listMaterialName) {
        List<Recycling> recyclingList = recyclingRepository.findByUserIdAndMaterialList_MaterialNameIn(userId, listMaterialName);
        return recyclingList.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public RecyclingResponseDTO create(RecyclingCreateDTO dto) {
        User user = userRepository.findById(dto.userId()).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));

        Recycling recycling = mapper.toEntity(dto);
        recycling.setUser(user);

        Recycling recyclingSaved = recyclingRepository.save(recycling);
        return mapper.toResponse(recyclingSaved);
    }

    @Override
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

    @Override
    @Transactional
    public RecyclingResponseDTO update(Long id, RecyclingUpdateDTO dto) {
        Recycling recyclingFound = recyclingRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Reciclagem não encontrada"));

        mapper.updateEntityFromDto(dto, recyclingFound);

        recyclingFound.setValidationDate(RecyclingValidator.recyclingStatusValidator(dto.status()));

        recyclingFound = recyclingRepository.save(recyclingFound);
        return mapper.toResponse(recyclingFound);
    }

    @Override
    @Transactional
    public void updatePartial(Long id, RecyclingPatchDTO dto) {
        Recycling recyclingFound = recyclingRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Reciclagem não encontrada"));

        mapper.patchEntityFromDto(dto, recyclingFound);

        if (dto.status() != null) {
            recyclingFound.setValidationDate(RecyclingValidator.recyclingStatusValidator(dto.status()));
        }

        recyclingRepository.save(recyclingFound);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!recyclingRepository.existsById(id)) {
            throw new ObjectNotFoundException("Reciclagem não encontrada");
        }
        recyclingRepository.deleteById(id);
    }

}
