package br.com.darioklein.ecopass.repository;

import br.com.darioklein.ecopass.domain.model.entity.User;
import br.com.darioklein.ecopass.domain.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    boolean existsByUser(User user);
}
