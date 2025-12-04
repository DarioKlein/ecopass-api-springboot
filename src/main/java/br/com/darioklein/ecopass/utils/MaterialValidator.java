package br.com.darioklein.ecopass.utils;

import br.com.darioklein.ecopass.domain.dto.materialDTO.MaterialUniqueField;
import br.com.darioklein.ecopass.domain.model.Material;
import br.com.darioklein.ecopass.repository.MaterialRepository;
import br.com.darioklein.ecopass.service.exception.DataIntegrityViolationException;

import java.util.List;

public class MaterialValidator {

    public static void validateUniqueFieldNameMaterial(MaterialRepository materialRepository, MaterialUniqueField dto, Material materialFound) {
        List<Material> allMaterialsList = materialRepository.findAll();

        for (Material material : allMaterialsList) {
            if ((materialFound == null || !material.getId().equals(materialFound.getId())) && StringUtils.normalize(material.getName()).trim().equals(StringUtils.normalize(dto.name()).trim())) {
                throw new DataIntegrityViolationException("O nome do material digitado já existe");
            }
        }
    }
}
