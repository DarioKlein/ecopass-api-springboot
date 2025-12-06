package br.com.darioklein.ecopass.repository;

import br.com.darioklein.ecopass.domain.model.entity.Recycling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecyclingRepository extends JpaRepository<Recycling,Long> {
}
