package br.com.darioklein.ecopass.domain.dto.walletDTO;

import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record WalletPatchDTO(
        @PositiveOrZero(message = "O saldo não pode ser negativo")
        BigDecimal availableBalance,

        @PositiveOrZero(message = "o saldo pendente não pode ser negativo")
        BigDecimal pendingBalance
) {
}
