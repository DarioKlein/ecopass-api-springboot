package br.com.darioklein.ecopass.repository;

import br.com.darioklein.ecopass.domain.model.entity.Recycling;
import br.com.darioklein.ecopass.domain.model.enumTypes.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RecyclingRepository extends JpaRepository<Recycling, Long> {
    Recycling findFirstByUserIdOrderByRegisterDateDesc(Long userId);

    List<Recycling> findByUserIdAndStatus(Long userId, Status status);

    List<Recycling> findByRegisterDateAfterOrValidationDateAfter(LocalDateTime registerDate, LocalDateTime validationDateAfter);

    List<Recycling> findByUserIdAndMaterialList_MaterialNameIn(Long userId, List<String> listMaterialName);
}