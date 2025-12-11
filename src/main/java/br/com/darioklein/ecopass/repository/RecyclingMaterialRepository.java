package br.com.darioklein.ecopass.repository;

import br.com.darioklein.ecopass.domain.model.entity.RecyclingMaterial;
import br.com.darioklein.ecopass.domain.model.entity.id.RecyclingMaterialId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RecyclingMaterialRepository extends JpaRepository<RecyclingMaterial, RecyclingMaterialId> {
    List<RecyclingMaterial> findById_RecyclingId(Long recyclingId);

    List<RecyclingMaterial> findByQuantityKgLessThan(BigDecimal quantityKgIsLessThan);

    List<RecyclingMaterial> findByQuantityKgGreaterThan(BigDecimal quantityKgIsGreaterThan);
}
