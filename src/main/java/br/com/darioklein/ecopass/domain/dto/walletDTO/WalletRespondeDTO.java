package br.com.darioklein.ecopass.domain.dto.walletDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public record WalletRespondeDTO(
        Long id,
        Long userId,
        BigDecimal availableBalance,
        BigDecimal pendingBalance,
        LocalDate createdAt
) {
}
