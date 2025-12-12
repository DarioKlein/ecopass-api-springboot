package br.com.darioklein.ecopass.repository;

import br.com.darioklein.ecopass.domain.model.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    List<Material> findAllByNameContainingIgnoreCase(String name);

    List<Material> findByPricePerKgLessThanEqual(BigDecimal pricePerKgIsLessThan);

    List<Material> findByPricePerKgGreaterThanEqual(BigDecimal pricePerKgIsGreaterThan);
}
