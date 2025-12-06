package br.com.darioklein.ecopass.repository;

import br.com.darioklein.ecopass.domain.model.entity.RecyclingMaterial;
import br.com.darioklein.ecopass.domain.model.entity.id.RecyclingMaterialId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecyclingMaterialRepository extends JpaRepository<RecyclingMaterial, RecyclingMaterialId> {
}
