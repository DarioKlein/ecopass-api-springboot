package br.com.darioklein.ecopass.repository;

import br.com.darioklein.ecopass.domain.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    boolean existsByName(String name);

    List<Material> findAllByNameContainingIgnoreCase(String name);
}
