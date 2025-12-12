package br.com.darioklein.ecopass.controller;

import br.com.darioklein.ecopass.domain.dto.materialDTO.MaterialResponseDTO;
import br.com.darioklein.ecopass.service.impl.UserMaterialFavoriteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserMaterialFavoriteController {

    private final UserMaterialFavoriteServiceImpl userMaterialFavoriteService;

    @GetMapping("/{userId}/favorites")
    public ResponseEntity<List<MaterialResponseDTO>> listUserMaterialFavorites(@PathVariable Long userId) {
        List<MaterialResponseDTO> materialList = userMaterialFavoriteService.listFavoriteMaterial(userId);
        return ResponseEntity.ok(materialList);
    }

    @PostMapping("/favorite")
    public ResponseEntity<Void> createUserMaterialFavorite(@RequestParam Long userId, @RequestParam Long materialId) {
        userMaterialFavoriteService.addFavoriteMaterial(userId, materialId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/favorite")
    public ResponseEntity<Void> deleteUserMaterialFavorite(@RequestParam Long userId, @RequestParam Long materialId) {
        userMaterialFavoriteService.removeFavoriteMaterial(userId, materialId);
        return ResponseEntity.noContent().build();
    }
}
