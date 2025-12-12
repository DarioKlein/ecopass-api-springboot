package br.com.darioklein.ecopass.service.impl;

import br.com.darioklein.ecopass.domain.dto.materialDTO.MaterialResponseDTO;
import br.com.darioklein.ecopass.domain.mapper.MaterialMapper;
import br.com.darioklein.ecopass.domain.model.entity.Material;
import br.com.darioklein.ecopass.domain.model.entity.User;
import br.com.darioklein.ecopass.repository.MaterialRepository;
import br.com.darioklein.ecopass.repository.UserRepository;
import br.com.darioklein.ecopass.service.UserMaterialFavoriteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UserMaterialFavoriteServiceImpl implements UserMaterialFavoriteService {

    private final UserRepository userRepository;
    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    @Override
    @Transactional(readOnly = true)
    public List<MaterialResponseDTO> listFavoriteMaterial(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        List<Material> materialList = user.getFavoriteMaterials();
        return materialList.stream().map(materialMapper::toResponse).toList();
    }

    @Override
    @Transactional
    public void addFavoriteMaterial(Long userId, Long materialId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Material material = materialRepository.findById(materialId).orElseThrow(() -> new RuntimeException("Material não encontrado"));

        user.getFavoriteMaterials().add(material);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeFavoriteMaterial(Long userId, Long materialId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Material material = materialRepository.findById(materialId).orElseThrow(() -> new RuntimeException("Material não encontrado"));

        user.getFavoriteMaterials().remove(material);
        userRepository.save(user);
    }
}
