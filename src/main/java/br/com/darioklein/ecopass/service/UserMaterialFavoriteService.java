package br.com.darioklein.ecopass.service;

import br.com.darioklein.ecopass.domain.dto.materialDTO.MaterialResponseDTO;

import java.util.List;

public interface UserMaterialFavoriteService {

    List<MaterialResponseDTO> listFavoriteMaterial(Long userId);

    void addFavoriteMaterial(Long userId, Long materialId);

    void removeFavoriteMaterial(Long userId, Long materialId);
}
